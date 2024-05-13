package hamusutax.core.collections.bytearray

import hamusutax.core.io.toBuffer
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
