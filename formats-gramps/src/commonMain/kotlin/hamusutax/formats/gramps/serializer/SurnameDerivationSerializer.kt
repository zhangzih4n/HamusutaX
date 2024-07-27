package hamusutax.formats.gramps.serializer

import hamusutax.formats.gramps.attribute.SurnameDerivation
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object SurnameDerivationSerializer : KSerializer<SurnameDerivation> {
    override val descriptor =
        PrimitiveSerialDescriptor("derivation", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: SurnameDerivation) {
        encoder.encodeString(value.value)
    }

    override fun deserialize(decoder: Decoder) =
        SurnameDerivation(decoder.decodeString())
}
