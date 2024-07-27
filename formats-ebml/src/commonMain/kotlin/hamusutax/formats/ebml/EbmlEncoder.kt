package hamusutax.formats.ebml

import hamusutax.number.toByteArrayShortest
import kotlinx.io.Buffer
import kotlinx.io.Sink
import kotlinx.io.readByteArray
import kotlinx.serialization.SerializationException
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.StructureKind
import kotlinx.serialization.descriptors.StructureKind.CLASS
import kotlinx.serialization.descriptors.StructureKind.LIST
import kotlinx.serialization.descriptors.StructureKind.MAP
import kotlinx.serialization.descriptors.StructureKind.OBJECT
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder

internal class EbmlEncoder(
    val ebml: Ebml,
    private val sink: Sink = Buffer()
) : AbstractEncoder() {
    override val serializersModule
        get() = ebml.serializersModule

    private val structures = mutableListOf<Buffer>()

    private fun write(source: ByteArray) =
        (structures.lastOrNull() ?: sink).write(source)

    private fun write(long: Long) =
        write(long.toByteArrayShortest())

    fun encodeByteArray(value: ByteArray) {
        write(value.size.toVInt())
        write(value)
    }

    override fun encodeString(value: String) {
        val bytes = value.encodeToByteArray()
        write(bytes.size.toVInt())
        write(bytes)
    }

    override fun encodeByte(value: Byte) =
        encodeLong(value.toLong())

    override fun encodeShort(value: Short) =
        encodeLong(value.toLong())

    override fun encodeInt(value: Int) =
        encodeLong(value.toLong())

    override fun encodeLong(value: Long) {
        val bytes = value.toByteArrayShortest()
        write(bytes.size.toVInt())
        write(bytes)
    }

    override fun encodeElement(descriptor: SerialDescriptor, index: Int): Boolean {
        val elementDescriptor = descriptor.getElementDescriptor(index)
        if (elementDescriptor.kind is PrimitiveKind || elementDescriptor.serialName == "kotlin.ByteArray") {
            val ebmlId = descriptor.getElementAnnotations(index).getEbmlIdOrNull()
                ?: elementDescriptor.annotations.getEbmlId()
            write(ebmlId.id)
        }
        return true
    }

    override fun beginStructure(descriptor: SerialDescriptor): CompositeEncoder {
        when (descriptor.kind as StructureKind) {
            CLASS, OBJECT -> structures.add(Buffer())
            LIST -> Unit
            MAP -> throw SerializationException("不支持 MAP 类型序列化！")
        }
        return this
    }

    override fun endStructure(descriptor: SerialDescriptor) {
        when (descriptor.kind as StructureKind) {
            CLASS, OBJECT -> {
                val bytes = structures.removeLast().readByteArray()
                val ebmlId = descriptor.annotations.getEbmlId()
                write(ebmlId.id)
                write(bytes.size.toVInt())
                write(bytes)
            }
            LIST -> Unit
            MAP -> throw SerializationException("不支持 MAP 类型序列化！")
        }
    }

    override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
        when (serializer.descriptor) {
            ByteArraySerializer().descriptor -> encodeByteArray(value as ByteArray)
            else -> super.encodeSerializableValue(serializer, value)
        }
    }
}
