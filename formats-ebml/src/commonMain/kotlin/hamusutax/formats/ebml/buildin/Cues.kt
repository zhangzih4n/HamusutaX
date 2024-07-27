package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x1C53BB6B)
@Serializable
data class Cues(
    val crc32: CRC32,
    val cuePoints: List<CuePoint>
)
