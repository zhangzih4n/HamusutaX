@file:Suppress("UNUSED")
package hamusutax.android.okhttp.cookiejar

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import hamusutax.android.sharedpreferences.clear
import hamusutax.android.sharedpreferences.getStringSetOrNull
import hamusutax.android.sharedpreferences.putStringSet
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class SharedPreferencesCookieJar(
    private val sharedPref: SharedPreferences
) : CookieJar {
    constructor(
        context: Context, name: String = "cookies", mode: Int = MODE_PRIVATE
    ) : this(context.getSharedPreferences(name, mode))

    override fun loadForRequest(url: HttpUrl) =
        (sharedPref.getStringSetOrNull(url.host) ?: emptyList())
            .map { Cookie.parse(url, it) ?: throw IllegalStateException("Cookie parsing error from SharedPreferences") }
            .filter { it.expiresAt > System.currentTimeMillis() && it.matches(url) }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        sharedPref.putStringSet(
            url.host, ((sharedPref.getStringSetOrNull(url.host) ?: emptyList())
                .map { Cookie.parse(url, it) ?: throw IllegalStateException("Cookie parsing error from SharedPreferences") } + cookies)
                .filter { it.expiresAt > System.currentTimeMillis() }
                .reversed() // 两次翻转以保留靠后的 Cookies
                .distinctBy { Triple(it.domain, it.name, it.path) }
                .reversed()
                .map { it.toString() }
                .toSet()
        )
    }

    operator fun get(url: HttpUrl) = loadForRequest(url)

    operator fun set(url: HttpUrl, cookies: List<Cookie>) = saveFromResponse(url, cookies)

    fun clear() = sharedPref.clear()
}
