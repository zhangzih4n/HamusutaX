package hamusutax.formats.ebml

import hamusutax.io.buffer.readLongAtLeastOne
import hamusutax.number.BitAction.ZERO
import hamusutax.number.setHighestOneBit
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.readByteArray
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind.LIST
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.CompositeDecoder.Companion.DECODE_DONE

internal abstract class AbstractEbmlDecoder : AbstractDecoder() {
    abstract val ebml: Ebml
    protected abstract val source: Source

    override val serializersModule
        get() = ebml.serializersModule

    fun decodeElement(): Buffer {
        val buffer = Buffer()
        buffer.write(decodeVInt(false))
        buffer.write(decodeByteArray())
        return buffer
    }

    fun decodeByteArray(): ByteArray {
        val size = decodeInteger(true)
        source.require(size)
        val bytes = source.readByteArray(size.toInt())
        return bytes
    }

    override fun decodeByte() =
        decodeInteger(true).toByte()

    override fun decodeShort() =
        decodeInteger(true).toShort()

    override fun decodeInt() =
        decodeInteger(true).toInt()

    override fun decodeLong() =
        decodeInteger(true)

    fun decodeVInt(removeMarker: Boolean): ByteArray {
        val buffer = Buffer()
        var readingWidth = true
        var remain = 1
        while (remain > 0) {
            remain--
            val byte = source.readByte()
            if (readingWidth) {
                if (byte == 0.toByte()) {
                    remain += 8
                } else {
                    remain += byte.countLeadingZeroBits()
                    readingWidth = false
                    if (removeMarker) {
                        buffer.writeByte(byte.setHighestOneBit(ZERO))
                        continue
                    }
                }
            }
            buffer.writeByte(byte)
        }
        return buffer.readByteArray()
    }

    /**
     * @param removeMarker 是否清除 VINT_MARKER。作为 EBML_ID 时不需要，作为整数数值时需要
     */
    internal fun decodeInteger(removeMarker: Boolean): Long {
        val bytes = decodeVInt(removeMarker)
        if (bytes.size > 8)
            throw SerializationException("EBML 字节长度大于 Long.MAX_VALUE！")
        val buffer = Buffer()
        buffer.write(bytes)
        return buffer.readLongAtLeastOne()
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>, previousValue: T?) =
        when (deserializer.descriptor) {
            ByteArraySerializer().descriptor -> decodeByteArray() as T
            else -> super.decodeSerializableValue(deserializer, previousValue)
        }
}

internal class EbmlDecoder(
    override val ebml: Ebml,
    source: Source,
    private val skipStructureFunc: Boolean = true
) : AbstractEbmlDecoder() {
    override val source = Buffer().apply { transferFrom(source) }
    private val stack = mutableListOf<Buffer>()

    override fun decodeElementIndex(descriptor: SerialDescriptor): Int {
//        println(source.peek().readByteArray().toHexString(UpperCase))
//        println(descriptor.serialName)
//        println(descriptor.annotations)
        println("decodeElementIndex: ${descriptor.kind} | ${descriptor.serialName}")

        while (true) {
            if (!source.request(1))
                return DECODE_DONE
            val decodedId = decodeInteger(false)
            for (index in 0..<descriptor.elementsCount) {
                val elementDescriptor = descriptor.getElementDescriptor(index)

                val ebmlId = if (elementDescriptor.kind != LIST || elementDescriptor.serialName == "kotlin.ByteArray") {
                    descriptor.getElementAnnotations(index).getEbmlIdOrNull()
                        ?: elementDescriptor.annotations.getEbmlId()
                } else {
                    elementDescriptor.getElementAnnotations(0).getEbmlIdOrNull()
                        ?: elementDescriptor.getElementDescriptor(0).annotations.getEbmlId()
                }

                if (ebmlId.id == decodedId)
                    return index
            }
            decodeByteArray()
        }
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeDecoder {
        println("beginStructure: ${descriptor.kind} | ${descriptor.serialName}")
        if (descriptor.isInline || skipStructureFunc) return this
        return EbmlDecoder(ebml, decodeElement(), false)
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        println("beginStructure: ${descriptor.kind} | ${descriptor.serialName}")
        if (descriptor.isInline) return
        source
    }
}
