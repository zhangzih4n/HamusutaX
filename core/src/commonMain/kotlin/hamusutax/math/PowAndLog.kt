@file:Suppress("unused")
package hamusutax.math

import kotlin.math.ceil
import kotlin.math.log
import kotlin.math.pow

/**
 * 是否为 n 的正整数次幂（包含零次幂）
 */
fun Int.isPowerOf(n: Int): Boolean {
    require(this > 0 && n > 0) { "Number and base must be greater than zero." }
    return when (n) {
        2 -> this and (this - 1) == 0
        else -> this == nextPowerOf(n)
    }
}

/**
 * 是否为 n 的正整数次幂（包含零次幂）
 */
fun Long.isPowerOf(n: Int): Boolean {
    require(this > 0 && n > 0) { "Number and base must be greater than zero." }
    return when (n) {
        2 -> this and (this - 1) == 0L
        else -> this == nextPowerOf(n)
    }
}

/**
 * 获取下一个 2 的整数次幂。
 * 当 Int 小于 0 时，返回值恒等于 0
 */
private fun Int.nextPowerOf2() =
    if (this and (this - 1) == 0) this
    else 2 shl 31 - countLeadingZeroBits()

/*
fun Int.nextPowerOf2(): Int {
    var n = this - 1
    n = n or (n shr 1)
    n = n or (n shr 2)
    n = n or (n shr 4)
    n = n or (n shr 8)
    n = n or (n shr 16)
    n = n or (n shr 24)
    return n + 1
}
 */

/**
 * 获取下一个 2 的整数次幂。
 * 当 Int 小于 0 时，返回值恒等于 0
 */
private fun Long.nextPowerOf2() =
    if (this and (this - 1) == 0L) this
    else 2L shl 63 - countLeadingZeroBits()

/*
Benchmark                      Mode  Cnt           Score           Error  Units
MyBenchmark.this              thrpt   10  3640998585.623 ± 108809370.035  ops/s
MyBenchmark.enabled           thrpt   10  3695534121.705 ± 111663320.118  ops/s

fun Long.nextPowerOf2(): Long {
    var n = this - 1
    n = n or (n shr 1)
    n = n or (n shr 2)
    n = n or (n shr 4)
    n = n or (n shr 8)
    n = n or (n shr 16)
    n = n or (n shr 24)
    n = n or (n shr 32)
    n = n or (n shr 40)
    n = n or (n shr 48)
    n = n or (n shr 56)
    return n + 1
}
 */

/**
 * 获取下一个 n 的整数次幂
 */
fun Int.nextPowerOf(n: Int): Int {
    require(this > 0 && n > 0) { "Number and base must be greater than zero." }
    return when (n) {
        2 -> nextPowerOf2()
        else -> {
            val value = n.toDouble().pow(ceil(log(this.toDouble(), n.toDouble())))
            require(value <= Int.MAX_VALUE) { "Result is greater than Int.MAX_VALUE." }
            value.toInt()
        }
    }
}

/**
 * 获取下一个 n 的整数次幂
 */
fun Long.nextPowerOf(n: Int): Long {
    require(this > 0 && n > 0) { "Number and base must be greater than zero." }
    return when (n) {
        2 -> nextPowerOf2()
        else -> {
            val value = n.toDouble().pow(ceil(log(this.toDouble(), n.toDouble())))
            require(value <= Long.MAX_VALUE) { "Result is greater than Long.MAX_VALUE." }
            value.toLong()
        }
    }
}

infix fun Int.pow(n: Int) =
    toLong().pow(n)

/**
 * 输入整数与参数需不小于 0
 *
 * 如果需要返回值为 Double，请使用 [Double.pow]
 *
 * 当结果超过 Long 的最大值时，返回 [Long.MAX_VALUE]
 */
infix fun Long.pow(n: Int): Long =
    if (n == 0) 1L // 标准库中 0.0.pow(0) == 1.0
    else when (this) {
        0L -> 0L
        1L -> 1L
        2L -> if (n <= 62) 1L shl n else Long.MAX_VALUE
        else -> {
            if (isPowerOf(2)) {
                val bits = 63 - countLeadingZeroBits()
                2L pow n * bits
            }
            else toDouble().pow(n).toLong()
        }
    }
