@file:Suppress("unused")
package hamusutax.ktor.client.cookies

import hamusutax.datetime.toCookieExpires
import hamusutax.ktor.utils.date.toInstant
import io.ktor.http.Cookie

fun Cookie.toCookieString() = buildString {
    append("$name=$value")
    expires?.let { append("; Expires=${it.toInstant().toCookieExpires()}") }
    append("; Max-Age=$maxAge")
    domain?.let { append("; Domain=$it") }
    path?.let { append("; Path=$it") }

    if (secure) append("; Secure")
    if (httpOnly) append("; HttpOnly")

    extensions.forEach { (n, v) ->
        append("; $n=$v")
    }
}
