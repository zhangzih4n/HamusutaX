@file:Suppress("unused")
package hamusutax.collections.sequence

import hamusutax.io.buffer.toBuffer
import kotlinx.io.readByteArray

fun Sequence<Byte>.toByteArray() =
    toBuffer().readByteArray()
