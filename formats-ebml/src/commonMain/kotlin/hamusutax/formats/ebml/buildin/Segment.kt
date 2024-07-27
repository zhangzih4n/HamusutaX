package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x18538067)
@Serializable
data class Segment(
    val seekHead: SeekHead,
    val void: Void,
    val info: Info
)
