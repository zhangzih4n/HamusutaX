@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.Buffer
import kotlinx.io.readUByte
import kotlinx.io.readUInt
import kotlinx.io.readULong
import kotlinx.io.readUShort

/**
 * 在 Buffer 开头插入空字节序列以达到指定长度
 * @param targetSize 目标长度，若不大于当前长度则不执行操作
 */
fun Buffer.padStart(targetSize: Long) {
    if (targetSize <= size) return
    val buffer = Buffer()
    var remain = targetSize - size
    while (remain > 0) {
        val current = minOf(remain, Int.MAX_VALUE.toLong()).toInt()
        buffer.write(ByteArray(current))
        remain -= current
    }
    readTo(buffer, size)
    buffer.readTo(this, buffer.size)
}

/**
 * 从该源中删除八个字节并返回按大端顺序由它组成的 Long
 *
 * 没有足够的数据时会在序列头部填充至八个字节再读取
 */
fun Buffer.readLongAtLeastOne(): Long {
    if (size >= 8) return readLong()
    val buffer = Buffer()
    buffer.write(ByteArray(8 - size.toInt()))
    readTo(buffer, size)
    return buffer.readLong()
}

/**
 * 从该源中删除八个字节并返回按大端顺序由它组成的 ULong
 *
 * 没有足够的数据时会在序列头部填充至八个字节再读取
 */
fun Buffer.readULongAtLeastOne(): ULong {
    if (size >= 8) return readULong()
    val buffer = Buffer()
    buffer.write(ByteArray(8 - size.toInt()))
    readTo(buffer, size)
    return buffer.readULong()
}

/**
 * 从该源中删除四个字节并返回按大端顺序由它组成的 Int
 *
 * 没有足够的数据时会在序列头部填充至四个字节再读取
 */
fun Buffer.readIntAtLeastOne(): Int {
    if (size >= 4) return readInt()
    val buffer = Buffer()
    buffer.write(ByteArray(4 - size.toInt()))
    readTo(buffer, size)
    return buffer.readInt()
}

/**
 * 从该源中删除四个字节并返回按大端顺序由它组成的 UInt
 *
 * 没有足够的数据时会在序列头部填充至四个字节再读取
 */
fun Buffer.readUIntAtLeastOne(): UInt {
    if (size >= 4) return readUInt()
    val buffer = Buffer()
    buffer.write(ByteArray(4 - size.toInt()))
    readTo(buffer, size)
    return buffer.readUInt()
}

/**
 * 从该源中删除两个字节并返回按大端顺序由它组成的 Short
 *
 * 没有足够的数据时会在序列头部填充至两个字节再读取
 */
fun Buffer.readShortAtLeastOne(): Short {
    if (size >= 2) return readShort()
    val buffer = Buffer()
    buffer.write(ByteArray(2 - size.toInt()))
    readTo(buffer, size)
    return buffer.readShort()
}

/**
 * 从该源中删除两个字节并返回按大端顺序由它组成的 UShort
 *
 * 没有足够的数据时会在序列头部填充至两个字节再读取
 */
fun Buffer.readUShortAtLeastOne(): UShort {
    if (size >= 2) return readUShort()
    val buffer = Buffer()
    buffer.write(ByteArray(2 - size.toInt()))
    readTo(buffer, size)
    return buffer.readUShort()
}

/**
 * 从该源中删除一个字节并返回
 *
 * 没有足够的数据时会返回零字节
 */
fun Buffer.readByteAtLeastOne(): Byte {
    if (size >= 1) return readByte()
    return 0.toByte()
}

/**
 * 从该源中删除一个字节并返回由它组成的 UByte
 *
 * 没有足够的数据时会返回零字节
 */
fun Buffer.readUByteAtLeastOne(): UByte {
    if (size >= 1) return readUByte()
    return 0.toUByte()
}
