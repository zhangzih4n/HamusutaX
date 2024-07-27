@file:Suppress("unused")
package hamusutax.io.outputstream

import kotlinx.io.asSink
import kotlinx.io.buffered
import java.io.OutputStream

fun OutputStream.buffered() =
    asSink().buffered()
