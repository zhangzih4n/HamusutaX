@file:Suppress("UNUSED")
package hamusutax.core.number

import kotlin.experimental.and
import kotlin.experimental.inv
import kotlin.experimental.or

infix fun Byte.shl(bitCount: Int) =
    (toInt() shl bitCount).toByte()

infix fun Short.shl(bitCount: Int) =
    (toInt() shl bitCount).toShort()

expect fun Long.bitsToDouble(): Double

expect fun Double.bitsToLong(): Long

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Byte.clearHighestOneBit() =
    this and takeHighestOneBit().inv()

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Short.clearHighestOneBit() =
    this and takeHighestOneBit().inv()

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Int.clearHighestOneBit() =
    this and takeHighestOneBit().inv()

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Long.clearHighestOneBit() =
    this and takeHighestOneBit().inv()

fun Byte.setHighestOneBit(): Byte {
    check(this >= 0)
    return if (this == 0.toByte()) 1
    else this or (takeHighestOneBit() shl 1)
}

fun Short.setHighestOneBit(): Short {
    check(this >= 0)
    return if (this == 0.toShort()) 1
    else this or (takeHighestOneBit() shl 1)
}

fun Int.setHighestOneBit(): Int {
    check(this >= 0)
    return if (this == 0) 1
    else this or (takeHighestOneBit() shl 1)
}

/**
 * 将二进制最高位 1 左侧的第一位设为 1
 */
fun Long.setHighestOneBit(): Long {
    check(this >= 0)
    return if (this == 0L) 1
    else this or (takeHighestOneBit() shl 1)
}

/**
 * 修改 Int 的二进制数值，将从右向左第 n bit 设为 1
 *
 * n 的取值范围为 1..32
 */
fun Int.setBit(bit: Int): Int {
    require(bit in 1..32)
    return this or (1 shl bit - 1)
}

/**
 * 修改 Long 的二进制数值，将从右向左第 n bit 设为 1
 *
 * n 的取值范围为 1..64
 */
fun Long.setBit(bit: Int): Long {
    require(bit in 1..64)
    return this or (1L shl bit - 1)
}
