package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x7373)
@Serializable
data class Tag(
    val targets: Targets,
    val simpleTags: List<SimpleTag>
)
