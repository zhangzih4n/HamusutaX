package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x114D9B74)
@Serializable
data class SeekHead(
    val seeks: List<Seek>
)
