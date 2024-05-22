@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Source

fun Source.isEmpty() = !request(1)
fun Source.isNotEmpty() = request(1)
