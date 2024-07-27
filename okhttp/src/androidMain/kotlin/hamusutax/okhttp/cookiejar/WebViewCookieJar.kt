@file:Suppress("unused")
package hamusutax.okhttp.cookiejar

import android.os.Build.VERSION_CODES
import android.webkit.CookieManager
import androidx.annotation.RequiresApi
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

@JvmInline
value class WebViewCookieJar(
    private val cookieManager: CookieManager = CookieManager.getInstance()!!
) : CookieJar {
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) =
        cookies.forEach { cookieManager.setCookie(url.toString(), it.toString()) }

    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val cookies = cookieManager.getCookie(url.toString())
        return if (cookies != null && cookies.isNotEmpty())
            cookies.split("; ").mapNotNull { Cookie.parse(url, it) }
        else emptyList()
    }

    @RequiresApi(VERSION_CODES.LOLLIPOP)
    fun clear() = cookieManager.removeAllCookies {}

    @RequiresApi(VERSION_CODES.LOLLIPOP)
    fun clear(callback: ((Boolean) -> Unit)?) = cookieManager.removeAllCookies(callback)
}
