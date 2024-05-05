@file:Suppress("UNUSED")
package hamusutax.core.number

actual fun Long.bitsToDouble() =
    java.lang.Double.longBitsToDouble(this)

actual fun Double.toLongBits() =
    java.lang.Double.doubleToLongBits(this)

/**
 * 返回整数的二进制补码表示，长度固定 32 个字符
 *
 * 当整数为正数时，等效于 Int.toString(2)
 */
actual fun Int.toBinaryString(trimStart: Boolean): String =
    Integer.toBinaryString(this).let {
        if (trimStart) it
        else it.padStart(32, '0')
    }
