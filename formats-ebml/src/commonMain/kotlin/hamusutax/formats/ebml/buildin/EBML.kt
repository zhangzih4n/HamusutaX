package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@Serializable
@EbmlID(0x1A45DFA3)
data class EBML(
    @EbmlID(0x4286) val ebmlVersion: Int,
    @EbmlID(0x42F7) val ebmlReadVersion: Int,
    @EbmlID(0x42F2) val ebmlMaxIDLength: Int,
    @EbmlID(0x42F3) val ebmlMaxSizeLength: Int,
    @EbmlID(0x4282) val docType: String,
    @EbmlID(0x4287) val docTypeVersion: Int,
    @EbmlID(0x4285) val docTypeReadVersion: Int
)
