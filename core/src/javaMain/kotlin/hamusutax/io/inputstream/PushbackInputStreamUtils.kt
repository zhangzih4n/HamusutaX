@file:Suppress("unused")
package hamusutax.io.inputstream

import java.io.PushbackInputStream

fun PushbackInputStream.peek() =
    read().also { unread(it) }
