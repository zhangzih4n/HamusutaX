package hamusutax.formats.gramps.serializer

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

object SurnamePrimarySerializer : KSerializer<Boolean> {
    override val descriptor =
        PrimitiveSerialDescriptor("prim", PrimitiveKind.STRING)

    override fun serialize(encoder: Encoder, value: Boolean) {
        encoder.apply {
            if (value) encodeNull() else encodeString("0")
        }
    }

    override fun deserialize(decoder: Decoder) =
        decoder.decodeString() != "0"
}
