@file:Suppress("UNUSED")
package hamusutax.io

import kotlinx.io.Buffer

fun ByteArray.toBuffer() =
    Buffer().apply { write(this@toBuffer) }
