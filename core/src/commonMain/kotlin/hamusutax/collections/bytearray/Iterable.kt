@file:Suppress("unused")
package hamusutax.collections.bytearray

import hamusutax.io.buffer.toBuffer
import kotlinx.io.readByteArray
import kotlinx.io.readByteString

/**
 * 展开字节序列列表
 */
fun Iterable<ByteArray>.toByteArray() =
    toBuffer().readByteArray()

/**
 * 展开字节序列列表
 */
fun Iterable<ByteArray>.toByteString() =
    toBuffer().readByteString()
