package hamusutax.core.number

actual fun Long.bitsToDouble(): Double =
    bitsToDouble()

actual fun Double.toLongBits(): Long =
    toLongBits()

/**
 * 返回整数的二进制补码表示，长度固定 32 个字符
 *
 * 当整数为正数时，等效于 Int.toString(2)
 */
actual fun Int.toBinaryString(trimStart: Boolean): String =
    toBinaryString(trimStart)
