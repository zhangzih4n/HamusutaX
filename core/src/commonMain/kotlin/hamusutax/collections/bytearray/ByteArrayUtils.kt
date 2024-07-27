@file:Suppress("unused")
package hamusutax.collections.bytearray

import kotlinx.io.Buffer
import kotlinx.io.readByteArray

/**
 * 在字节序列前部填充
 * @throws IllegalArgumentException 目标大小小于原大小
 */
fun ByteArray.padStart(newSize: Int, padByte: Byte = 0) =
    if (newSize > size) {
        ByteArray(newSize - size).apply {
            if (padByte != 0.toByte()) fill(padByte)
        } + this
    } else this

/**
 * 在字节序列尾部填充
 */
fun ByteArray.padEnd(newSize: Int, padByte: Byte = 0) =
    if (newSize > size) {
        this + ByteArray(newSize - size).apply {
            if (padByte != 0.toByte()) fill(padByte)
        }
    } else this

/**
 * 将字节序列转为二进制字符串
 */
fun ByteArray.toBinString() =
    joinToString("") { it.toString(2).padStart(8, '0') }

fun String.binToByteArray(): ByteArray {
    require(length % 8 == 0)
    val buffer = Buffer()
    chunked(8).forEach {
        buffer.writeByte(it.toByte(2))
    }
    return buffer.readByteArray()
}

/**
 * 将字节序列调整至指定大小，若小于当前大小则从尾部截断，若大于当前大小则使用特定字节填充（默认为零）
 */
fun ByteArray.sliceArrayOrPadEnd(newSize: Int, padByte: Byte = 0) =
    if (newSize == size) this
    else if (newSize < size) sliceArray(0..<newSize)
    else padEnd(newSize, padByte)


fun ByteArray.chunked(size: Int): List<ByteArray> {
    val list = mutableListOf<ByteArray>()
    for (i in indices step size) {
        list.add(sliceArray(i..<minOf(i + size, this.size)))
    }
    return list
}
