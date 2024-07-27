package hamusutax.formats.gramps.serializer

import hamusutax.formats.gramps.attribute.Type
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object TypeSerializer : KSerializer<Type> {
    override val descriptor =
        PrimitiveSerialDescriptor("type", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Type) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder) =
        Type(decoder.decodeString())
}
