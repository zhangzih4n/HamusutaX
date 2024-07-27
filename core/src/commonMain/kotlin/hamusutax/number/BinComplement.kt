@file:Suppress("unused")
package hamusutax.number

/**
 * 返回 Byte 的二进制补码表示，默认长度 8 个字符
 */
fun Byte.toBinComplement(): String {
    val unsigned =
        if (this >= 0) toUByte() else ((-this).toUByte().inv() + 1u).toUByte()
    return unsigned.toString(2).padStart(8, '0')
}

/**
 * 返回 Short 的二进制补码表示，默认长度 16 个字符
 */
fun Short.toBinComplement(): String {
    val unsigned =
        if (this >= 0) toUShort() else ((-this).toUShort().inv() + 1u).toUShort()
    return unsigned.toString(2).padStart(16, '0')
}

/**
 * 返回 Int 的二进制补码表示，默认长度 32 个字符
 */
expect fun Int.toBinComplement(): String

/**
 * 返回 Long 的二进制补码表示，默认长度 64 个字符
 */
fun Long.toBinComplement(): String {
    val unsigned =
        if (this >= 0) toULong() else (-this).toULong().inv() + 1uL
    return unsigned.toString(2).padStart(64, '0')
}

fun ByteArray.toBinComplement() =
    joinToString("") { it.toBinComplement() }
