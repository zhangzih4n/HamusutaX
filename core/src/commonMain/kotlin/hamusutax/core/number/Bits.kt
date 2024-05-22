@file:Suppress("UNUSED")
package hamusutax.core.number

import kotlin.experimental.and
import kotlin.experimental.inv

expect fun Long.bitsToDouble(): Double
expect fun Double.toLongBits(): Long

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Byte.clearHighestOneBit(): Byte =
    this and takeHighestOneBit().inv()

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Short.clearHighestOneBit(): Short =
    this and takeHighestOneBit().inv()

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Int.clearHighestOneBit(): Int =
    this and takeHighestOneBit().inv()

/**
 * 将二进制最高位 1 设置为 0。如果不存在最高位 1，则返回零
 */
fun Long.clearHighestOneBit(): Long =
    this and takeHighestOneBit().inv()
