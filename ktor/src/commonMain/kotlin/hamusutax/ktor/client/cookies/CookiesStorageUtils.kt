@file:Suppress("unused")
package hamusutax.ktor.client.cookies

import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url

/**
 * 使 [Url] 的 Cookie 失效
 *
 * 一般情况下，Max-Age 设为 0 会立即从 [CookiesStorage] 中移除 Cookies；
 * 设为 -1 会在 Session 断开时移除。
 *
 * @param url 需要失效的 [Cookie] 的 [Url]
 * @param name 需要失效的 [Cookie] 的名称
 */
internal suspend fun CookiesStorage.invalidate(url: Url, name: String, maxAge: Int = 0): Int {
    val cookies = get(url)
    val needRemoveCookie = cookies.find { it.name == name }
        ?.let { Cookie(it.name, "", maxAge = maxAge) }
        ?: return 0
    addCookie(url, needRemoveCookie)
    return 1
}

/**
 * 使 [Url] 的 Cookies 失效
 *
 * 一般情况下，Max-Age 设为 0 会立即从 [CookiesStorage] 中移除 Cookies；
 * 设为 -1 会在 Session 断开时移除。
 *
 * @param url 需要失效的 [Cookie] 的 [Url]
 * @param names 需要失效的 [Cookie] 的名称
 */
internal suspend fun CookiesStorage.invalidate(url: Url, names: List<String>, maxAge: Int = 0): Int {
    val cookies = get(url)
    val needRemoveCookies = cookies.filter { it.name in names }
        .map { Cookie(it.name, "", maxAge = maxAge) }
    needRemoveCookies.forEach { cookie ->
        addCookie(url, cookie)
    }
    return needRemoveCookies.count()
}

/**
 * 使 [Url] 的所有 Cookies 失效
 *
 * 一般情况下，Max-Age 设为 0 会立即从 [CookiesStorage] 中移除 [Cookie]；
 * 设为 -1 会在 Session 断开时移除。
 *
 * @param url 需要失效的 Cookies 的 [Url]
 */
internal suspend fun CookiesStorage.invalidate(url: Url, maxAge: Int = 0): Int {
    val cookies = get(url)
    val needRemoveCookies = cookies
        .map { Cookie(it.name, "", maxAge = maxAge) }
    needRemoveCookies.forEach { cookie ->
        addCookie(url, cookie)
    }
    return needRemoveCookies.count()
}
