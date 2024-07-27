@file:Suppress("unused")
package hamusutax.number

import kotlinx.io.Buffer
import kotlinx.io.readByteArray
import kotlinx.io.writeIntLe
import kotlinx.io.writeLongLe
import kotlinx.io.writeShortLe
import kotlinx.io.writeUByte
import kotlinx.io.writeUInt
import kotlinx.io.writeUIntLe
import kotlinx.io.writeULong
import kotlinx.io.writeULongLe
import kotlinx.io.writeUShort
import kotlinx.io.writeUShortLe

fun Byte.toByteArray() =
    Buffer().apply { writeByte(this@toByteArray) }.readByteArray()

fun Short.toByteArray() =
    Buffer().apply { writeShort(this@toByteArray) }.readByteArray()

fun Short.toByteArrayLe() =
    Buffer().apply { writeShortLe(this@toByteArrayLe) }.readByteArray()

fun Int.toByteArray(): ByteArray {
    val bytes = ByteArray(4)
    for (n in 0..3) {
        bytes[n] = (this shr (3 - n) * 8).toByte()
    }
    return bytes
}

fun Int.toByteArrayLe() =
    Buffer().apply { writeIntLe(this@toByteArrayLe) }.readByteArray()

fun Long.toByteArray(): ByteArray {
    val bytes = ByteArray(8)
    for (n in 0..7) {
        bytes[n] = (this shr (7 - n) * 8).toByte()
    }
    return bytes
}

fun Long.toByteArrayLe() =
    Buffer().apply { writeLongLe(this@toByteArrayLe) }.readByteArray()

fun UByte.toByteArray() =
    Buffer().apply { writeUByte(this@toByteArray) }.readByteArray()

fun UShort.toByteArray() =
    Buffer().apply { writeUShort(this@toByteArray) }.readByteArray()

fun UShort.toByteArrayLe() =
    Buffer().apply { writeUShortLe(this@toByteArrayLe) }.readByteArray()

fun UInt.toByteArray() =
    Buffer().apply { writeUInt(this@toByteArray) }.readByteArray()

fun UInt.toByteArrayLe() =
    Buffer().apply { writeUIntLe(this@toByteArrayLe) }.readByteArray()

fun ULong.toByteArray() =
    Buffer().apply { writeULong(this@toByteArray) }.readByteArray()

fun ULong.toByteArrayLe() =
    Buffer().apply { writeULongLe(this@toByteArrayLe) }.readByteArray()

fun Short.toByteArrayShortest(): ByteArray {
    return when (this) {
        0.toShort() -> ByteArray(1)
        (-1).toShort() -> byteArrayOf(-1)
        else -> {
            val bytes = toByteArray()
            if (this > 0) {
                for (i in 0..1) {
                    if (bytes[i] != 0.toByte())
                        return bytes.sliceArray(i..1)
                }
            } else {
                for (i in 0..1) {
                    if (bytes[i] > 0)
                        return bytes.sliceArray(i - 1..1)
                    if (bytes[i] != (-1).toByte())
                        return bytes.sliceArray(i..1)
                }
            }
            throw IllegalArgumentException("Short 转换至最短 ByteArray 时遇到错误！($this)")
        }
    }
}

fun Int.toByteArrayShortest(): ByteArray {
    return when (this) {
        0 -> ByteArray(1)
        -1 -> byteArrayOf(-1)
        else -> {
            val bytes = toByteArray()
            if (this > 0) {
                for (i in 0..3) {
                    if (bytes[i] != 0.toByte())
                        return bytes.sliceArray(i..3)
                }
            } else {
                for (i in 0..3) {
                    if (bytes[i] > 0)
                        return bytes.sliceArray(i - 1..3)
                    if (bytes[i] != (-1).toByte())
                        return bytes.sliceArray(i..3)
                }
            }
            throw IllegalArgumentException("Int 转换至最短 ByteArray 时遇到错误！($this)")
        }
    }
}

fun Long.toByteArrayShortest(): ByteArray {
    return when (this) {
        0L -> ByteArray(1)
        -1L -> byteArrayOf(-1)
        else -> {
            val bytes = toByteArray()
            if (this > 0) {
                for (i in 0..7) {
                    if (bytes[i] != 0.toByte())
                        return bytes.sliceArray(i..7)
                }
            } else {
                for (i in 0..7) {
                    if (bytes[i] > 0)
                        return bytes.sliceArray(i - 1..7)
                    if (bytes[i] != (-1).toByte())
                        return bytes.sliceArray(i..7)
                }
            }
            throw IllegalArgumentException("Long 转换至最短 ByteArray 时遇到错误！($this)")
        }
    }
}
