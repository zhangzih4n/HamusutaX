@file:Suppress("UNUSED")
package hamusutax.core.number

@Deprecated(message = "TODO", level = DeprecationLevel.ERROR)
actual fun Long.bitsToDouble(): Double = TODO()

@Deprecated(message = "TODO", level = DeprecationLevel.ERROR)
actual fun Double.toLongBits(): Long = TODO()

actual fun Int.twosComplement(trimStart: Boolean): String =
    commonTwosComplement(trimStart)

actual fun Long.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Short.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Byte.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)
