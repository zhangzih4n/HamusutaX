package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@EbmlID(0xA3)
@Serializable
@JvmInline
value class SimpleBlock(
    val value: ByteArray
)
