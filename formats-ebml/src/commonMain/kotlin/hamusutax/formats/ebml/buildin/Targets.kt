package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x63C0)
@Serializable
data class Targets(
    @EbmlID(0x63C0) val targetTypeValue: Int
)
