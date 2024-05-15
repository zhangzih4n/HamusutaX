@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Buffer

@JvmName("byteArrayToBuffer")
fun ByteArray.toBuffer() =
    Buffer().apply { write(this@toBuffer) }

@JvmName("byteArrayIterableToBuffer")
fun Iterable<ByteArray>.toBuffer() =
    Buffer().apply { forEach { write(it) } }

@JvmName("byteArraySequenceToBuffer")
fun Sequence<ByteArray>.toBuffer() =
    Buffer().apply { forEach { write(it) } }

@JvmName("byteIterableToBuffer")
fun Iterable<Byte>.toBuffer() =
    Buffer().apply { forEach { writeByte(it) } }

@JvmName("byteSequenceToBuffer")
fun Sequence<Byte>.toBuffer() =
    Buffer().apply { forEach { writeByte(it) } }
