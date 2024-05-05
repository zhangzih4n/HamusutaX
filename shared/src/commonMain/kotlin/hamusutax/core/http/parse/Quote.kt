package hamusutax.core.http.parse

/**
 * 进行 `UrlEncode` 编码（空格将编码为 `"%20"`）
 */
expect fun String.quote(): String

/**
 * 进行 `UrlEncode` 编码（空格将编码为加号）
 */
expect fun String.quotePlus(): String

/**
 * 对 UrlDecode 解码（不解码加号）
 */
expect fun String.unquote(): String

/**
 * 对 UrlDecode 解码（加号将解码为空格）
 */
expect fun String.unquotePlus(): String
