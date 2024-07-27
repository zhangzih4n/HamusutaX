//@file:Suppress("unused")
package hamusutax.number

import hamusutax.number.BitAction.ONE
import hamusutax.number.BitAction.REVERSED
import hamusutax.number.BitAction.ZERO
import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or
import kotlin.experimental.xor

/**
 * @param bitCount `0..7`
 */
inline infix fun Byte.shl(bitCount: Int) =
    (toInt() shl (bitCount and 0x7)).toByte()

/**
 * @param bitCount `0..15`
 */
inline infix fun Short.shl(bitCount: Int) =
    (toInt() shl (bitCount and 0xF)).toShort()

/**
 * @param bitCount `0..7`
 */
inline infix fun Byte.shr(bitCount: Int) =
    (toInt() shr (bitCount and 0x7)).toByte()

/**
 * @param bitCount `0..15`
 */
inline infix fun Short.shr(bitCount: Int) =
    (toInt() shr (bitCount and 0xF)).toShort()

/**
 * @param bitCount `0..7`
 */
inline infix fun Byte.ushr(bitCount: Int) =
    ((toInt() and 0xFF) ushr (bitCount and 0x7)).toByte()

/**
 * @param bitCount `0..15`
 */
inline infix fun Short.ushr(bitCount: Int) =
    ((toInt() and 0xFFFF) ushr (bitCount and 0xF)).toShort()

inline infix fun Int.leftRotate(bitCount: Int) =
    (this shl bitCount) or (this ushr (32 - bitCount))

inline infix fun Long.rightRotate(bitCount: Int) =
    (this ushr bitCount) or (this shl (64 - bitCount))

fun Short.reverseBytes(): Short {
    val i = toInt() and 0xFFFF
    val reversed = (i and 0xFF00 ushr 8) or (i and 0xFF shl 8)
    return reversed.toShort()
}

fun Int.reverseBytes() =
    (this and -0x1000000 ushr 24) or
    (this and 0xFF0000 ushr 8) or
    (this and 0xFF00 shl 8) or
    (this and 0xFF shl 24)

fun Long.reverseBytes() =
    (this and -0x100000000000000L ushr 56) or
    (this and 0xFF000000000000L ushr 40) or
    (this and 0xFF0000000000L ushr 24) or
    (this and 0xFF00000000L ushr 8) or
    (this and 0xFF000000L shl 8) or
    (this and 0xFF0000L shl 24) or
    (this and 0xFF00L shl 40) or
    (this and 0xFFL shl 56)

enum class BitAction {
    ZERO, ONE, REVERSED
}

/**
 * 修改 Byte 的二进制数值，将从右向左第 n 位设为指定值
 *
 * n 的取值范围为 1..8
 */
fun Byte.setBit(bit: Int, value: BitAction = ONE): Byte {
    require(bit in 1..8)
    return when (value) {
        ZERO -> this and (1.toByte() shl bit - 1).inv()
        ONE -> this or (1.toByte() shl bit - 1)
        REVERSED -> this xor (1.toByte() shl bit - 1)
    }
}

/**
 * 修改 Short 的二进制数值，将从右向左第 n bit 设为 1
 *
 * n 的取值范围为 1..16
 */
fun Short.setBit(bit: Int, value: BitAction = ONE): Short {
    require(bit in 1..16)
    return when (value) {
        ZERO -> this and (1.toShort() shl bit - 1).inv()
        ONE -> this or (1.toShort() shl bit - 1)
        REVERSED -> this xor (1.toShort() shl bit - 1)
    }
}

/**
 * 修改 Int 的二进制数值，将从右向左第 n bit 设为 1
 *
 * n 的取值范围为 1..32
 */
fun Int.setBit(bit: Int, value: BitAction = ONE): Int {
    require(bit in 1..32)
    return when (value) {
        ZERO -> this and (1 shl bit - 1).inv()
        ONE -> this or (1 shl bit - 1)
        REVERSED -> this xor (1 shl bit - 1)
    }
}

/**
 * 修改 Long 的二进制数值，将从右向左第 n bit 设为 1
 *
 * n 的取值范围为 1..64
 */
fun Long.setBit(bit: Int, value: BitAction = ONE): Long {
    require(bit in 1..64)
    return when (value) {
        ZERO -> this and (1L shl bit - 1).inv()
        ONE -> this or (1L shl bit - 1)
        REVERSED -> this xor (1L shl bit - 1)
    }
}

/**
 * 将二进制最高位 1 左侧的第一位设为指定值。
 * 若二进制为全 0，则应用到最低位
 */
fun Byte.setHighestOneBit(value: BitAction = ONE) =
    if (this == 0.toByte()) 1
    else setBit(8 - countLeadingZeroBits(), value)

/**
 * 将二进制最高位 1 左侧的第一位设为指定值。
 * 若二进制为全 0，则应用到最低位
 */
fun Short.setHighestOneBit(value: BitAction = ONE) =
    if (this == 0.toShort()) {
        when (value) {
            ZERO -> 0
            ONE, REVERSED -> 1
        }
    } else setBit(16 - countLeadingZeroBits(), value)

/**
 * 将二进制最高位 1 左侧的第一位设为指定值。
 * 若二进制为全 0，则应用到最低位
 */
fun Int.setHighestOneBit(value: BitAction = ONE) =
    if (this == 0) {
        when (value) {
            ZERO -> 0
            ONE, REVERSED -> 1
        }
    } else setBit(32 - countLeadingZeroBits(), value)

/**
 * 将二进制最高位 1 左侧的第一位设为 1
 */
fun Long.setHighestOneBit(value: BitAction = ONE) =
    if (this == 0L) {
        when (value) {
            ZERO -> 0
            ONE, REVERSED -> 1
        }
    } else setBit(64 - countLeadingZeroBits(), value)
