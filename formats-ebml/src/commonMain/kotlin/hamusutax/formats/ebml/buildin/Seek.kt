package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@Serializable
@EbmlID(0x4DBB)
data class Seek(
    @EbmlID(0x53AB) val seekID: ByteArray,
    @EbmlID(0x53AC) val seekPosition: Long
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as Seek
        if (!seekID.contentEquals(other.seekID)) return false
        if (seekPosition != other.seekPosition) return false
        return true
    }

    override fun hashCode(): Int {
        var result = seekID.contentHashCode()
        result = 31 * result + seekPosition.hashCode()
        return result
    }
}
