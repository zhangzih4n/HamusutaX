@file:Suppress("UNUSED")
package hamusutax.core.number

/**
 * 返回 Byte 的二进制补码表示，默认长度 8 个字符
 */
expect fun Byte.twosComplement(trimStart: Boolean = false): String

internal fun Byte.commonTwosComplement(trimStart: Boolean = false) =
    if (this >= 0) {
        toString(2).let {
            if (trimStart) it else it.padStart(8, '0')
        }
    } else (-this - 1).toString(2)
        .padStart(8, '0')
        .map { if (it == '0') 1 else 0 }
        .joinToString("")


/**
 * 返回 Short 的二进制补码表示，默认长度 16 个字符
 */
expect fun Short.twosComplement(trimStart: Boolean = false): String

internal fun Short.commonTwosComplement(trimStart: Boolean = false) =
    if (this >= 0) {
        toString(2).let {
            if (trimStart) it else it.padStart(16, '0')
        }
    } else (-this - 1).toString(2)
        .padStart(16, '0')
        .map { if (it == '0') 1 else 0 }
        .joinToString("")

/**
 * 返回 Int 的二进制补码表示，默认长度 32 个字符
 */
expect fun Int.twosComplement(trimStart: Boolean = false): String

internal fun Int.commonTwosComplement(trimStart: Boolean = false) =
    if (this >= 0) {
        toString(2).let {
            if (trimStart) it else it.padStart(32, '0')
        }
    } else (-this - 1).toString(2)
        .padStart(32, '0')
        .map { if (it == '0') 1 else 0 }
        .joinToString("")

/**
 * 返回 Long 的二进制补码表示，默认长度 64 个字符
 */
expect fun Long.twosComplement(trimStart: Boolean = false): String

internal fun Long.commonTwosComplement(trimStart: Boolean) =
    if (this >= 0) {
        toString(2).let {
            if (trimStart) it else it.padStart(64, '0')
        }
    } else (-this - 1).toString(2)
        .padStart(64, '0')
        .map { if (it == '0') 1 else 0 }
        .joinToString("")

fun ByteArray.twosComplements(trimStart: Boolean = false) = sequence {
    forEach { yield(it.twosComplement(trimStart)) }
}
