@file:Suppress("UNUSED")
package hamusutax.core.serialization.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlin.io.encoding.Base64

object ByteArrayHexUpperCaseSerializer : KSerializer<ByteArray> {
    override val descriptor = PrimitiveSerialDescriptor("ByteArrayHexUpperCase", STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.decodeString().hexToByteArray()

    override fun serialize(encoder: Encoder, value: ByteArray) =
        encoder.encodeString(value.toHexString(HexFormat.UpperCase))
}

object ByteArrayHexLowerCaseSerializer : KSerializer<ByteArray> {
    override val descriptor = PrimitiveSerialDescriptor("ByteArrayHexLowerCase", STRING)

    override fun deserialize(decoder: Decoder) =
        decoder.decodeString().hexToByteArray()

    override fun serialize(encoder: Encoder, value: ByteArray) =
        encoder.encodeString(value.toHexString())
}

object ByteArrayBase64Serializer : KSerializer<ByteArray> {
    override val descriptor = PrimitiveSerialDescriptor("ByteArrayBase64", STRING)

    override fun deserialize(decoder: Decoder) =
        Base64.decode(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: ByteArray) =
        encoder.encodeString(Base64.encode(value))
}

object ByteArrayBase64UrlSafeSerializer : KSerializer<ByteArray> {
    override val descriptor = PrimitiveSerialDescriptor("ByteArrayBase64UrlSafe", STRING)

    override fun deserialize(decoder: Decoder) =
        Base64.UrlSafe.decode(decoder.decodeString())

    override fun serialize(encoder: Encoder, value: ByteArray) =
        encoder.encodeString(Base64.UrlSafe.encode(value))
}
