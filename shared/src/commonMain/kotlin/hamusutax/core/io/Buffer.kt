@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Buffer

fun ByteArray.toBuffer() =
    Buffer().apply { write(this@toBuffer) }
