@file:Suppress("UNUSED")
package hamusutax.ktor.client.cookies

import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url

/**
 * 立即从 [CookiesStorage] 中移除 Cookies
 *
 * 原理为将 Value 设为空字符串并将 Max-Age 设为 0
 *
 * @param url 需要移除的 [Cookie] 的 [Url]
 * @param names 需要移除的 [Cookie] 的名称，为 null 时将清除域名所有 Cookies
 */
suspend fun CookiesStorage.removeNow(url: Url, names: List<String>? = null) =
    remove(url, names, 0)

suspend fun CookiesStorage.removeNow(url: Url, name: String) =
    remove(url, listOf(name), 0)

/**
 * 在 Session 断开时从 [CookiesStorage] 中移除 Cookies
 *
 * 原理为将 Value 设为空字符串并将 Max-Age 设为 0
 *
 * @param url 需要移除的 [Cookie] 的 [Url]
 * @param names 需要移除的 [Cookie] 的名称，为 null 时将清除域名所有 Cookies
 */
suspend fun CookiesStorage.removeAtEndOfSession(url: Url, names: List<String>? = null) =
    remove(url, names, -1)

suspend fun CookiesStorage.removeAtEndOfSession(url: Url, name: String) =
    remove(url, listOf(name), -1)

internal suspend fun CookiesStorage.remove(url: Url, names: List<String>?, maxAge: Int): Int {
    val cookies = get(url)
    val needRemoveCookies = cookies.filter { if (names == null) true else it.name in names }
        .map { Cookie(it.name, "", maxAge = maxAge) }
    needRemoveCookies.forEach { cookie ->
        addCookie(url, cookie)
    }
    return needRemoveCookies.count()
}
