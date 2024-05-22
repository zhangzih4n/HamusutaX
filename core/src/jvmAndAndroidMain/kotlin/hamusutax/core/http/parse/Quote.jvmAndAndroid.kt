@file:Suppress("UNUSED")
package hamusutax.core.http.parse

import java.net.URLDecoder
import java.net.URLEncoder

actual fun String.quote() =
    URLEncoder.encode(this, "utf-8").replace("+", "%20")

actual fun String.quotePlus(): String =
    URLEncoder.encode(this, "utf-8")

actual fun String.unquote(): String =
    URLDecoder.decode(this, "utf-8")

actual fun String.unquotePlus() =
    URLDecoder.decode(this, "utf-8").replace("+", " ")
