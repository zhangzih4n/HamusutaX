@file:Suppress("unused")
package hamusutax.collections.iterable

import hamusutax.io.buffer.toBuffer
import kotlinx.io.readByteArray

fun Iterable<Byte>.toByteArray() =
    toBuffer().readByteArray()
