@file:Suppress("UNUSED")
package hamusutax.jvm.io.inputstream

import java.io.InputStream
import java.io.PushbackInputStream

fun InputStream.toPushbackInputStream(size: Int = 1) =
    PushbackInputStream(this, size)
