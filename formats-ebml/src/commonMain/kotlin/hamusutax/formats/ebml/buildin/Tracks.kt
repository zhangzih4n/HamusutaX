package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x1654AE6B)
@Serializable
data class Tracks(
    val crc32: CRC32,
    val trackEntry: TrackEntry
)
