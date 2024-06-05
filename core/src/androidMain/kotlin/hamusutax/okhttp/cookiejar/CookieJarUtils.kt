@file:Suppress("UNUSED")
package hamusutax.okhttp.cookiejar

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

operator fun CookieJar.get(url: HttpUrl) =
    loadForRequest(url)

operator fun CookieJar.set(url: HttpUrl, cookies: List<Cookie>) =
    saveFromResponse(url, cookies)

/**
 * 立即从 [CookieJar] 中移除 Cookies
 *
 * 原理为将 Value 设为空字符串并将 Max-Age 设为 0
 *
 * @param url 需要移除的 [Cookie] 的 [HttpUrl]
 * @param names 需要移除的 [Cookie] 的名称，为 null 时将清除域名所有 Cookies
 */
fun CookieJar.removeNow(url: HttpUrl, names: List<String>? = null) =
    remove(url, names, 0)

/**
 * 在 Session 断开时从 [CookieJar] 中移除 Cookies
 *
 * 原理为将 Value 设为空字符串并将 Max-Age 设为 0
 *
 * @param url 需要移除的 [Cookie] 的 [HttpUrl]
 * @param names 需要移除的 [Cookie] 的名称，为 null 时将清除域名所有 Cookies
 */
fun CookieJar.removeAtEndOfSession(url: HttpUrl, names: List<String>? = null) =
    remove(url, names, -1)

internal fun CookieJar.remove(url: HttpUrl, names: List<String>?, maxAge: Int): Int {
    val cookies = loadForRequest(url)
    val needRemoveCookies = cookies.filter { if (names == null) true else it.name in names }
        .map { Cookie.parse(url, "${it.name}=; Max-Age=$maxAge")!! }
    saveFromResponse(url, needRemoveCookies)
    return needRemoveCookies.count()
}
