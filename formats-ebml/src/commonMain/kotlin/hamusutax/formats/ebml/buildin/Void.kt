package hamusutax.formats.ebml.buildin

import hamusutax.formats.ebml.EbmlID
import kotlinx.serialization.Serializable
import kotlin.jvm.JvmInline

@EbmlID(0xEC)
@Serializable
@JvmInline
value class Void(
    val value: ByteArray
)
