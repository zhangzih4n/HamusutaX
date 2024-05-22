@file:Suppress("UNUSED")
package hamusutax.core.collections.iterable

import hamusutax.core.io.toBuffer
import kotlinx.io.readByteArray

fun Iterable<Byte>.toByteArray() =
    toBuffer().readByteArray()
