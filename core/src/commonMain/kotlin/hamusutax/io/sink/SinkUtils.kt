@file:Suppress("unused")
package hamusutax.io.sink

import kotlinx.io.Sink
import kotlinx.io.writeString

fun Sink.writeChar(char: Char) =
    writeString(char.toString())
