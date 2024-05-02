@file:Suppress("UNUSED")
package hamusutax.http.parse

import java.net.URLDecoder
import java.net.URLEncoder
import java.nio.charset.Charset

/**
 * 进行 `UrlEncode` 编码（空格将编码为 `"%20"`）
 */
fun String.quote(charset: Charset = Charsets.UTF_8): String =
    URLEncoder.encode(this, charset.name()).replace("+", "%20")

/**
 * 进行 `UrlEncode` 编码（空格将编码为加号）
 */
fun String.quotePlus(charset: Charset = Charsets.UTF_8): String =
    URLEncoder.encode(this, charset.name())

/**
 * 对 UrlDecode 解码（不解码加号）
 */
fun String.unquote(charset: Charset = Charsets.UTF_8): String =
    URLDecoder.decode(this, charset.name())

/**
 * 对 UrlDecode 解码（加号将解码为空格）
 */
fun String.unquotePlus(charset: Charset = Charsets.UTF_8): String =
    URLDecoder.decode(this, charset.name()).replace("+", " ")
