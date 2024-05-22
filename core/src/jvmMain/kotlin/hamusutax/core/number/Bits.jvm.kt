@file:Suppress("UNUSED")
package hamusutax.core.number

actual fun Long.bitsToDouble() =
    java.lang.Double.longBitsToDouble(this)

actual fun Double.toLongBits() =
    java.lang.Double.doubleToLongBits(this)

actual fun Int.twosComplement(trimStart: Boolean): String =
    Integer.toBinaryString(this).let {
        if (trimStart) it else it.padStart(32, '0')
    }

actual fun Long.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Short.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Byte.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)
