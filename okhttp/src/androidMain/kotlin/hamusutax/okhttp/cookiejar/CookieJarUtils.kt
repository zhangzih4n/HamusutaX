@file:Suppress("unused")
package hamusutax.okhttp.cookiejar

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

operator fun CookieJar.get(url: HttpUrl) =
    loadForRequest(url)

operator fun CookieJar.set(url: HttpUrl, cookies: List<Cookie>) =
    saveFromResponse(url, cookies)
