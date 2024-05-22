@file:Suppress("UNUSED")
package hamusutax.core.number

actual fun Long.bitsToDouble(): Double =
    bitsToDouble()

actual fun Double.toLongBits(): Long =
    toLongBits()

actual fun Int.twosComplement(trimStart: Boolean): String =
    twosComplement(trimStart)

actual fun Long.twosComplement(trimStart: Boolean): String =
    twosComplement(trimStart)

actual fun Short.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Byte.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun ByteArray.twosComplements(trimStart: Boolean): Iterator<String> {
    TODO("Not yet implemented")
}
