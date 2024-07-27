package hamusutax.text

import kotlinx.io.bytestring.ByteString
import kotlinx.io.bytestring.decodeToString
import kotlinx.io.bytestring.toHexString

/**
 * 将字节序列以 UTF-8 编码解码为字符串。若解码失败则返回 null
 */
fun ByteArray.decodeToStringOrNull() =
    runCatching {
        decodeToString().also { require('�' !in it) }
    }.getOrNull()

/**
 * 将字节序列以 UTF-8 编码解码为字符串。若解码失败则返回 null
 */
fun ByteString.decodeToStringOrNull() =
    runCatching {
        decodeToString().also { require('�' !in it) }
    }.getOrNull()

/**
 * 将字节序列以 UTF-8 编码解码为字符串。若解码失败则转为 HexString
 */
fun ByteArray.decodeToStringOrHexString(
    prefix: String = "",
    postfix: String = "",
    hexFormat: HexFormat = HexFormat.Default,
) =
    decodeToStringOrNull() ?: (prefix + toHexString(hexFormat) + postfix)

/**
 * 将字节序列以 UTF-8 编码解码为字符串。若解码失败则转为 HexString
 */
fun ByteString.decodeToStringOrHexString(
    prefix: String = "",
    postfix: String = "",
    hexFormat: HexFormat = HexFormat.Default
) =
    decodeToStringOrNull() ?: (prefix + toHexString(hexFormat) + postfix)
