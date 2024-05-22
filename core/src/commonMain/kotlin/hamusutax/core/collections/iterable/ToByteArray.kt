@file:Suppress("UNUSED")
package hamusutax.core.collections.iterable

import hamusutax.core.io.buffer.toBuffer
import kotlinx.io.readByteArray

fun Iterable<Byte>.toByteArray() =
    toBuffer().readByteArray()
