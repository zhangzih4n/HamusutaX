@file:Suppress("UNUSED")
package hamusutax.jvm.io.inputstream

import java.io.PushbackInputStream

fun PushbackInputStream.peek() =
    read().also { unread(it) }
