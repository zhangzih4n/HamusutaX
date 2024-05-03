@file:Suppress("UNUSED")
package hamusutax.http.parse

/**
 * 进行 `UrlEncode` 编码（空格将编码为 `"%20"`）
 */
actual fun String.quote(): String =
    quote()

/**
 * 进行 `UrlEncode` 编码（空格将编码为加号）
 */
actual fun String.quotePlus(): String =
    quotePlus()

/**
 * 对 UrlDecode 解码（不解码加号）
 */
actual fun String.unquote(): String =
    unquote()

/**
 * 对 UrlDecode 解码（加号将解码为空格）
 */
actual fun String.unquotePlus(): String =
    unquotePlus()
