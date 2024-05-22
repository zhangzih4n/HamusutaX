@file:Suppress("UNUSED")
package hamusutax.core.collections.sequence

import hamusutax.core.io.toBuffer
import kotlinx.io.readByteArray

fun Sequence<Byte>.toByteArray() =
    toBuffer().readByteArray()
