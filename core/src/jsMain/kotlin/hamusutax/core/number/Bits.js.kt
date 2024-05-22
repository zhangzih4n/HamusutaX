@file:Suppress("UNUSED")
package hamusutax.core.number

@Deprecated(message = "TODO", level = DeprecationLevel.ERROR)
actual fun Double.toLongBits(): Long = TODO()

@Deprecated(message = "TODO", level = DeprecationLevel.ERROR)
actual fun Long.bitsToDouble(): Double = TODO()

actual fun Int.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Long.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Short.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)

actual fun Byte.twosComplement(trimStart: Boolean) =
    commonTwosComplement(trimStart)
