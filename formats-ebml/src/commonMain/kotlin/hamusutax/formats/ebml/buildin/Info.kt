package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@Serializable
@EbmlID(0x1549A966)
data class Info(
    val crc32: CRC32,
    @EbmlID(0x2AD7B1) val timestampScale: Long,
    @EbmlID(0x4D80) val muxingApp: String,
    @EbmlID(0x5741) val writingApp: String,
    @EbmlID(0x73A4) val segmentUUID: ByteArray,
    @EbmlID(0x4489) val duration: Double
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Info
        if (crc32 != other.crc32) return false
        if (timestampScale != other.timestampScale) return false
        if (muxingApp != other.muxingApp) return false
        if (writingApp != other.writingApp) return false
        if (!segmentUUID.contentEquals(other.segmentUUID)) return false
        if (duration != other.duration) return false

        return true
    }

    override fun hashCode(): Int {
        var result = crc32.hashCode()
        result = 31 * result + timestampScale.hashCode()
        result = 31 * result + muxingApp.hashCode()
        result = 31 * result + writingApp.hashCode()
        result = 31 * result + segmentUUID.contentHashCode()
        result = 31 * result + duration.hashCode()
        return result
    }
}
