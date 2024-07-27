package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0x1F43B675)
@Serializable
data class Cluster(
    val crc32: CRC32,
    val timestamp: Timestamp,
    val simpleBlocks: List<SimpleBlock>
)
