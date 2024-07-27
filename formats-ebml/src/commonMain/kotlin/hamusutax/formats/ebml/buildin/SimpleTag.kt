package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x67C8)
@Serializable
data class SimpleTag(
    @EbmlID(0x45A3) val tagName: String,
    @EbmlID(0x4487) val tagString: String,
    @EbmlID(0x447A) val tagLanguage: String? = null,
    @EbmlID(0x4484) val tagDefault: Int? = null
)
