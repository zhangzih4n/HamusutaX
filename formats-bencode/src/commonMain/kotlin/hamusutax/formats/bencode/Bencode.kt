@file:Suppress("unused")
package hamusutax.formats.bencode

import hamusutax.io.source.source
import kotlinx.io.Buffer
import kotlinx.io.Source
import kotlinx.io.bytestring.ByteString
import kotlinx.io.readByteArray
import kotlinx.io.readByteString
import kotlinx.serialization.BinaryFormat
import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.modules.EmptySerializersModule
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.serializer

sealed class Bencode(
    val configuration: BencodeConfiguration,
    override val serializersModule: SerializersModule
) : BinaryFormat {
    override fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
        val buffer = Buffer()
        StreamingBencodeEncoder(this, buffer).encodeSerializableValue(serializer, value)
        return buffer.readByteArray()
    }

    inline fun <reified T> encodeToByteArray(value: T) =
        encodeToByteArray(serializer(), value)

    fun <T> encodeToByteString(serializer: SerializationStrategy<T>, value: T): ByteString {
        val buffer = Buffer()
        StreamingBencodeEncoder(this, buffer).encodeSerializableValue(serializer, value)
        return buffer.readByteString()
    }

    inline fun <reified T> encodeToByteString(value: T) =
        encodeToByteString(serializer(), value)

    fun <T> encodeToBencodeElement(serializer: SerializationStrategy<T>, value: T): BencodeElement {
        val buffer = Buffer()
        StreamingBencodeEncoder(this, buffer).encodeSerializableValue(serializer, value)
        return decodeFrom<BencodeElement>(buffer)
    }

    inline fun <reified T> encodeToBencodeElement(value: T) =
        encodeToBencodeElement(serializer(), value)

    fun <T> encodeTo(serializer: SerializationStrategy<T>, buffer: Buffer, value: T) =
        StreamingBencodeEncoder(this, buffer).encodeSerializableValue(serializer, value)

    inline fun <reified T> encodeTo(buffer: Buffer, value: T) =
        encodeTo(serializer(), buffer, value)

    override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray) =
        StreamingBencodeDecoder(this, bytes.source()).decodeSerializableValue(deserializer)

    inline fun <reified T> decodeFromByteArray(bytes: ByteArray) =
        decodeFromByteArray(serializer<T>(), bytes)

    fun <T> decodeFromByteString(deserializer: DeserializationStrategy<T>, bytes: ByteString) =
        StreamingBencodeDecoder(this, bytes.source()).decodeSerializableValue(deserializer)

    inline fun <reified T> decodeFromByteString(bytes: ByteString) =
        decodeFromByteString(serializer<T>(), bytes)

    fun <T> decodeFrom(deserializer: DeserializationStrategy<T>, source: Source) =
        StreamingBencodeDecoder(this, source).decodeSerializableValue(deserializer)

    inline fun <reified T> decodeFrom(source: Source) =
        decodeFrom(serializer<T>(), source)

    inline fun <reified T> decodeFromBencodeElement(element: BencodeElement) =
        decodeFromBencodeElement(serializer<T>(), element)

    fun <T> decodeFromBencodeElement(deserializer: DeserializationStrategy<T>, element: BencodeElement): T {
        val buffer = Buffer()
        encodeTo(buffer, element)
        return decodeFrom(deserializer, buffer)
    }

    companion object Default : Bencode(BencodeConfiguration(), EmptySerializersModule())
}
