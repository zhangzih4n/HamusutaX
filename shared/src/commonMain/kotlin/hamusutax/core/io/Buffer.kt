@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Buffer

fun ByteArray.toBuffer() =
    Buffer().apply { write(this@toBuffer) }

fun Iterable<ByteArray>.toBuffer() =
    Buffer().apply { forEach { write(it) } }

fun Sequence<ByteArray>.toBuffer() =
    Buffer().apply { forEach { write(it) } }

fun Iterable<Byte>.toBuffer() =
    Buffer().apply { forEach { writeByte(it) } }

fun Sequence<Byte>.toBuffer() =
    Buffer().apply { forEach { writeByte(it) } }
