package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0xBB)
@Serializable
data class CuePoint(
    @EbmlID(0xF7) val cueTrack: Int,
    @EbmlID(0xF1) val cueClusterPosition: Int,
    @EbmlID(0xF0) val cueRelativePosition: Int,
)
