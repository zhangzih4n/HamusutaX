@file:Suppress("UNUSED")
package hamusutax.okhttp

import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import okhttp3.Cookie
import okhttp3.HttpUrl
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

inline fun buildCookie(builderAction: Cookie.Builder.() -> Unit): Cookie {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Cookie.Builder().apply(builderAction).build()
}

fun String.toCookie(host: String) =
    toCookieOrNull(host)!!

fun String.toCookieOrNull(host: String) =
    toCookieOrNull(
        buildHttpUrl {
            scheme("https")
            host(host)
        }
    )

fun String.toCookie(httpUrl: HttpUrl) =
    Cookie.parse(httpUrl, this) ?: throw IllegalStateException()

fun String.toCookieOrNull(httpUrl: HttpUrl) =
    Cookie.parse(httpUrl, this)

/**
 * 为 Cookie 填入 Domain 与 Path，若已有则跳过
 */
fun Cookie.fillDefaults(requestUrl: HttpUrl): Cookie {
    val result = this.newBuilder()

    if (!path.startsWith("/")) {
        result.path(requestUrl.encodedPath)
    }

    if (domain.isBlank()) {
        result.domain(requestUrl.host)
    }

    return result.build()
}
