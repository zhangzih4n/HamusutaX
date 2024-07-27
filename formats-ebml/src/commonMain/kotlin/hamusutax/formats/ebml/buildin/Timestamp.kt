package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@EbmlID(0xE7)
@Serializable
@JvmInline
value class Timestamp(
    val value: ByteArray
)
