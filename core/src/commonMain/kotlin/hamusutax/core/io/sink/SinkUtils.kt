@file:Suppress("UNUSED")
package hamusutax.core.io.sink

import kotlinx.io.Sink
import kotlinx.io.writeString

fun Sink.writeChar(char: Char) =
    writeString(char.toString())
