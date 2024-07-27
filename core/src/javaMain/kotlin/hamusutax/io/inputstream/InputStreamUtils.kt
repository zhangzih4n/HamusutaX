@file:Suppress("unused")
package hamusutax.io.inputstream

import kotlinx.io.asSource
import kotlinx.io.buffered
import java.io.InputStream

fun InputStream.buffered() =
    asSource().buffered()
