package hamusutax.formats.bencode

import kotlinx.io.Sink
import kotlinx.io.writeString
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.AbstractEncoder
import kotlinx.serialization.encoding.CompositeEncoder
import kotlinx.serialization.encoding.Encoder

interface BencodeEncoder : Encoder, CompositeEncoder {
    val bencode: Bencode

    override val serializersModule
        get() = bencode.serializersModule

    fun encodeBencodeElement(value: BencodeElement)
}

open class SinkBencodeEncoder(
    override val bencode: Bencode,
    open val sink: Sink
) : AbstractEncoder(), BencodeEncoder {
    override fun encodeByte(value: Byte) =
        encodeLong(value.toLong())

    override fun encodeShort(value: Short) =
        encodeLong(value.toLong())

    override fun encodeInt(value: Int) =
        encodeLong(value.toLong())

    override fun encodeLong(value: Long) =
        sink.writeString("$INTEGER_START$value$END_CHAR")

    override fun encodeString(value: String) =
        encodeByteArray(value.encodeToByteArray())

    open fun encodeByteArray(value: ByteArray) {
        sink.writeString("${value.size}:")
        sink.write(value)
    }

    override fun encodeBencodeElement(value: BencodeElement) {
        when (value) {
            is BencodeList -> {
                sink.writeString(LIST_START)
                value.forEach {
                    encodeBencodeElement(it)
                }
                sink.writeString(END)
            }
            is BencodeDictionary -> {
                sink.writeString(DICTIONARY_START)
                value.forEach { (key ,value) ->
                    encodeBencodeElement(key)
                    encodeBencodeElement(value)
                }
                sink.writeString(END)
            }
            is BencodeByteString -> encodeByteArray(value.bytes.toByteArray())
            is BencodeInteger -> encodeLong(value.long)
        }
    }

    override fun <T : Any> encodeNullableSerializableElement(
        descriptor: SerialDescriptor,
        index: Int,
        serializer: SerializationStrategy<T>,
        value: T?
    ) {
        if (value != null)
            super.encodeNullableSerializableElement(descriptor, index, serializer, value)
    }

    override fun <T> encodeSerializableValue(serializer: SerializationStrategy<T>, value: T) {
        when (serializer.descriptor) {
            ByteArraySerializer().descriptor -> encodeByteArray(value as ByteArray)
            BencodeElementSerializer.descriptor -> encodeBencodeElement(value as BencodeElement)
            else -> super<AbstractEncoder>.encodeSerializableValue(serializer, value)
        }
    }
}
