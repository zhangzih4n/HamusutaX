package hamusutax.formats.gramps.serializer

import hamusutax.formats.gramps.attribute.NameType
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object NameTypeSerializer : KSerializer<NameType> {
    override val descriptor =
        PrimitiveSerialDescriptor("prim", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: NameType) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder) =
        NameType(decoder.decodeString())
}
