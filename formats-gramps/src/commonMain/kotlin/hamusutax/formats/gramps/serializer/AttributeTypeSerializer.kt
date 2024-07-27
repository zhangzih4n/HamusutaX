package hamusutax.formats.gramps.serializer

import hamusutax.formats.gramps.attribute.AttributeType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object AttributeTypeSerializer : KSerializer<AttributeType> {
    override val descriptor =
        PrimitiveSerialDescriptor("type", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: AttributeType) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder) =
        AttributeType(decoder.decodeString())
}
