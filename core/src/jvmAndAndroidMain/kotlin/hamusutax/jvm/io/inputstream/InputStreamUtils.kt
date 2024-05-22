@file:Suppress("UNUSED")
package hamusutax.jvm.io.inputstream

import kotlinx.io.Buffer
import kotlinx.io.transferFrom
import java.io.InputStream

fun InputStream.readToBuffer() =
    Buffer().transferFrom(this)
