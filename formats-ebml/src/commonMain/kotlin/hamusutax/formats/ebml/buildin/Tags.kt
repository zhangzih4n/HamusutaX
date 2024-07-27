package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x1254C367)
@Serializable
data class Tags(
    val tags: List<Tag>
)
