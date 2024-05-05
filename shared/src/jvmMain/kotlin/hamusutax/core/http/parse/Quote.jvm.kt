package hamusutax.core.http.parse

import java.net.URLDecoder
import java.net.URLEncoder

/**
 * 进行 `UrlEncode` 编码（空格将编码为 `"%20"`）
 */
actual fun String.quote() =
    URLEncoder.encode(this, "utf-8").replace("+", "%20")

/**
 * 进行 `UrlEncode` 编码（空格将编码为加号）
 */
actual fun String.quotePlus(): String =
    URLEncoder.encode(this, "utf-8")

/**
 * 对 UrlDecode 解码（不解码加号）
 */
actual fun String.unquote(): String =
    URLDecoder.decode(this, "utf-8")

/**
 * 对 UrlDecode 解码（加号将解码为空格）
 */
actual fun String.unquotePlus() =
    URLDecoder.decode(this, "utf-8").replace("+", " ")
