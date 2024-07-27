package hamusutax.formats.bencode

import hamusutax.io.source.peekByteAsChar
import hamusutax.io.source.readByteAsChar
import kotlinx.io.Source
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.builtins.ByteArraySerializer
import kotlinx.serialization.encoding.AbstractDecoder
import kotlinx.serialization.encoding.CompositeDecoder
import kotlinx.serialization.encoding.Decoder

interface BencodeDecoder : Decoder, CompositeDecoder {
    val bencode: Bencode
    override val serializersModule
        get() = bencode.serializersModule

    fun decodeBencodeElement(): BencodeElement
}

abstract class SourceBencodeDecoder(
    override val bencode: Bencode,
    open val source: Source
) : AbstractDecoder(), BencodeDecoder {
    override fun decodeInt() =
        decodeLong().toInt()

    override fun decodeShort() =
        decodeLong().toShort()

    override fun decodeString() =
        decodeByteArray().decodeToString()

    abstract fun decodeByteArray(): ByteArray

    @Suppress("UNCHECKED_CAST")
    override fun <T : Any> decodeNullableSerializableValue(deserializer: DeserializationStrategy<T?>) =
        when (deserializer.descriptor) {
            ByteArraySerializer().descriptor -> decodeByteArray() as T?
            else -> super<AbstractDecoder>.decodeNullableSerializableValue(deserializer)
        }

    @Suppress("UNCHECKED_CAST")
    override fun <T> decodeSerializableValue(deserializer: DeserializationStrategy<T>, previousValue: T?) =
        when (deserializer.descriptor) {
            ByteArraySerializer().descriptor -> decodeByteArray() as T
            else -> super<AbstractDecoder>.decodeSerializableValue(deserializer, previousValue)
        }

    override fun decodeBencodeElement() =
        when (val char = source.peekByteAsChar()) {
            in INTEGER_CHARSET -> decodeBencodeByteString()
            INTEGER_START_CHAR -> decodeBencodeInteger()
            LIST_START_CHAR -> decodeBencodeList()
            DICTIONARY_START_CHAR -> decodeBencodeDictionary()
            else -> throw BencodeDecodingException("$char")
        }

    private fun decodeBencodeDictionary(): BencodeDictionary {
        require(source.readByteAsChar() == DICTIONARY_START_CHAR)
        val elements = buildList {
            while (true) {
                if (source.peekByteAsChar() == END_CHAR) {
                    source.readByte()
                    break
                }
                add(decodeBencodeElement())
            }
        }
        val elementMap = elements.chunked(2)
            .associate { (key, value) ->
                require(key is BencodeByteString)
                key to value
            }
        return BencodeDictionary(elementMap)
    }

    private fun decodeBencodeList(): BencodeList {
        require(source.readByteAsChar() == LIST_START_CHAR)
        val elements = buildList {
            while (true) {
                if (source.peekByteAsChar() == END_CHAR) {
                    source.readByte()
                    break
                }
                add(decodeBencodeElement())
            }
        }
        return BencodeList(elements)
    }

    private fun decodeBencodeByteString() =
        BencodeByteString(decodeByteArray())

    private fun decodeBencodeInteger() =
        BencodeInteger(decodeLong())
}
