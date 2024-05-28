@file:Suppress("UNUSED")
package hamusutax.ktor.client.cookies

import android.os.Build.VERSION_CODES
import android.webkit.CookieManager
import androidx.annotation.RequiresApi
import io.ktor.client.plugins.cookies.CookiesStorage
import io.ktor.http.Cookie
import io.ktor.http.Url
import org.jetbrains.annotations.ApiStatus.Experimental

@Experimental
class WebViewCookiesStorage: CookiesStorage {
    private val cookieManager = CookieManager.getInstance()!!

    override suspend fun addCookie(requestUrl: Url, cookie: Cookie) {
        cookieManager.setCookie(requestUrl.toString(), Url.toString())
    }

    override fun close() {}

    override suspend fun get(requestUrl: Url): List<Cookie> {
        val cookies = cookieManager.getCookie(requestUrl.toString())
        if (cookies == null || cookies.isNotEmpty()) return emptyList()

        return cookies.split("; ").map {
            val (key, value) = it.split("=")
            Cookie(key, value)
        }
    }

    @RequiresApi(VERSION_CODES.LOLLIPOP)
    fun clear(callback: ((Boolean) -> Unit)? = null) = cookieManager.removeAllCookies(callback)
}
