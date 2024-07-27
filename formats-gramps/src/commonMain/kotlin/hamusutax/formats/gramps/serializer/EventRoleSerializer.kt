package hamusutax.formats.gramps.serializer

import hamusutax.formats.gramps.attribute.EventRole
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object EventRoleSerializer : KSerializer<EventRole> {
    override val descriptor =
        PrimitiveSerialDescriptor("role", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: EventRole) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder) =
        EventRole(decoder.decodeString())
}
