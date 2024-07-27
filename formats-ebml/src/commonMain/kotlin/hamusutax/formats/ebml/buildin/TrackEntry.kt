package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable

@EbmlID(0xAE)
@Serializable
data class TrackEntry(
    @EbmlID(0xD7) val trackNumber: Int,
    @EbmlID(0x73C5) val trackUID: Long,
    @EbmlID(0x9C) val flagLacing: Int,
    @EbmlID(0x22B59C) val language: String,
    @EbmlID(0x86) val codecID: String,
    @EbmlID(0x83) val trackType: Int,
    val audio: Audio,
    @EbmlID(0x63A2) val codecPrivate: ByteArray
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false
        other as TrackEntry
        if (trackNumber != other.trackNumber) return false
        if (trackUID != other.trackUID) return false
        if (flagLacing != other.flagLacing) return false
        if (language != other.language) return false
        if (codecID != other.codecID) return false
        if (trackType != other.trackType) return false
        if (audio != other.audio) return false
        if (!codecPrivate.contentEquals(other.codecPrivate)) return false
        return true
    }

    override fun hashCode(): Int {
        var result = trackNumber
        result = 31 * result + trackUID.hashCode()
        result = 31 * result + flagLacing
        result = 31 * result + language.hashCode()
        result = 31 * result + codecID.hashCode()
        result = 31 * result + trackType
        result = 31 * result + audio.hashCode()
        result = 31 * result + codecPrivate.contentHashCode()
        return result
    }
}
