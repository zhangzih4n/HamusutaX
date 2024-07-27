@file:Suppress("unused")
package io.buffer

import kotlinx.io.Buffer
import okio.ByteString

fun ByteString.toBuffer() =
    Buffer().apply { write(this@toBuffer.toByteArray()) }
