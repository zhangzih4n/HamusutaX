package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@EbmlID(0xBF)
@Serializable
@JvmInline
value class CRC32(
    val value: ByteArray
)
