package hamusutax.number

expect fun Long.bitsToDouble(): Double

expect fun Double.toLongBits(): Long

/**
 * 返回整数的二进制补码表示，长度固定 32 个字符
 *
 * 当整数为正数时，等效于 Int.toString(2)
 */
expect fun Int.toBinaryString(trimStart: Boolean = false): String
