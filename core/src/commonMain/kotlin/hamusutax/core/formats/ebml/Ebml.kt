package hamusutax.core.formats.ebml

import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

sealed interface EbmlElement

@Serializable
class EbmlString(private val bytes: ByteArray): EbmlElement {
    val size get() = bytes.size

    operator fun get(index: Int) = bytes[index]


    override fun hashCode() = bytes.contentHashCode()

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class != other::class) return false

        other as EbmlString

        if (!bytes.contentEquals(other.bytes)) return false
        if (size != other.size) return false

        return true
    }

    override fun toString() = bytes.decodeToString()
}

@JvmInline
@Serializable
value class EbmlInteger(val value: Long) : EbmlElement

@JvmInline
@Serializable
value class EbmlUnsignedInteger(val value: ULong) : EbmlElement

@JvmInline
@Serializable
value class EbmlFloat(val value: Double) : EbmlElement

@JvmInline
@Serializable
value class EbmlMaster(val value: Long) : EbmlElement
