@file:Suppress("UNUSED")
package hamusutax.jvm.io

import java.io.InputStream
import java.io.PushbackInputStream

fun InputStream.toPushbackInputStream(size: Int = 1) =
    PushbackInputStream(this, size)

fun PushbackInputStream.peek() =
    read().also { unread(it) }
