@file:Suppress("UNUSED")
package hamusutax.core.number

actual fun Long.bitsToDouble() =
    java.lang.Double.longBitsToDouble(this)

actual fun Double.bitsToLong() =
    java.lang.Double.doubleToLongBits(this)

actual fun Int.twosComplement(trimStart: Boolean): String =
    Integer.toBinaryString(this).let {
        if (trimStart) it else it.padStart(32, '0')
    }
