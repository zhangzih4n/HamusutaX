@file:Suppress("UNUSED")
package hamusutax.core.io.buffer

import kotlinx.io.Buffer

fun Buffer.isEmpty() = size == 0L
fun Buffer.isNotEmpty() = size != 0L
