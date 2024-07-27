@file:Suppress("unused")
package hamusutax.formats.ebml

import hamusutax.io.source.source
import kotlinx.io.Buffer
import kotlinx.io.Sink
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

sealed class Ebml(
    val configuration: EbmlConfiguration,
    override val serializersModule: SerializersModule
) : BinaryFormat {
    fun <T> encodeTo(serializer: SerializationStrategy<T>, sink: Sink, value: T) {
        EbmlEncoder(this, sink).encodeSerializableValue(serializer, value)
    }

    inline fun <reified T> encodeTo(sink: Sink, value: T) =
        encodeTo(serializer(), sink, value)

    override fun <T> encodeToByteArray(serializer: SerializationStrategy<T>, value: T): ByteArray {
        val buffer = Buffer()
        EbmlEncoder(this, buffer).encodeSerializableValue(serializer, value)
        return buffer.readByteArray()
    }

    inline fun <reified T> encodeToByteArray(value: T) =
        encodeToByteArray(serializer(), value)

    fun <T> encodeToByteString(serializer: SerializationStrategy<T>, value: T): ByteString {
        val buffer = Buffer()
        EbmlEncoder(this, buffer).encodeSerializableValue(serializer, value)
        return buffer.readByteString()
    }

    inline fun <reified T> encodeToByteString(value: T) =
        encodeToByteString(serializer(), value)

    fun <T> decodeFromSource(deserializer: DeserializationStrategy<T>, source: Source) =
        EbmlDecoder(this, source).decodeSerializableValue(deserializer)

    inline fun <reified T> decodeFromSource(source: Source) =
        decodeFromSource<T>(serializer(), source)

    override fun <T> decodeFromByteArray(deserializer: DeserializationStrategy<T>, bytes: ByteArray) =
        EbmlDecoder(this, bytes.source()).decodeSerializableValue(deserializer)

    inline fun <reified T> decodeFromByteArray(bytes: ByteArray) =
        decodeFromByteArray<T>(serializer(), bytes)

    companion object Default : Ebml(EbmlConfiguration(), EmptySerializersModule())
}
