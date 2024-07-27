package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0xE1)
@Serializable
data class Audio(
    @EbmlID(0x9F) val channels: Int,
    @EbmlID(0xB5) val samplingFrequency: Double,
    @EbmlID(0x6264) val bitDepth: Int
)
