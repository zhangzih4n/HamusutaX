@file:Suppress("unused")
package hamusutax.number

/**
 * 将整数对齐到指定基数
 */
fun Int.alignTo(base: Int) =
    ((this + base - 1) / base) * base

/**
 * 将整数对齐到指定基数
 */
fun Long.alignTo(base: Int) =
    ((this + base - 1) / base) * base
