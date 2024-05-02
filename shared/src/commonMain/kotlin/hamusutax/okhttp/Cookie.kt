@file:Suppress("UNUSED")
package hamusutax.okhttp

import okhttp3.Cookie
import okhttp3.HttpUrl
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
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
    toCookieOrNull(httpUrl)!!

fun String.toCookieOrNull(httpUrl: HttpUrl) =
    Cookie.parse(httpUrl, this)
