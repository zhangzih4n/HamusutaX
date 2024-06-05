@file:Suppress("UNUSED")
package hamusutax.okhttp.cookiejar

import android.content.SharedPreferences
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import hamusutax.android.sharedpreferences.clear
import hamusutax.android.sharedpreferences.getStringSetOrNull
import hamusutax.android.sharedpreferences.putStringSet
import hamusutax.core.datetime.getTimeMillis
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

class SharedPreferencesCookieJar(
    private val sharedPref: SharedPreferences
) : CookieJar {
    @RequiresApi(VERSION_CODES.HONEYCOMB)
    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        val now = getTimeMillis()
        val cookieStrings = sharedPref.getStringSetOrNull(url.host) ?: return emptyList()
        return cookieStrings.mapNotNull { Cookie.parse(url, it) }
            .toMutableList().apply { cleanup(url, now) }
    }

    @RequiresApi(VERSION_CODES.HONEYCOMB)
    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val cookieStrings = sharedPref.getStringSetOrNull(url.host)
            ?.mapNotNull { Cookie.parse(url, it) } ?: emptyList()

        val now = getTimeMillis()
        val filteredCookies = cookieStrings.toMutableList().apply { cleanup(url, now) }
            .map { it.toString() }.toSet()

        sharedPref.putStringSet(url.host, filteredCookies)
    }

    @RequiresApi(VERSION_CODES.GINGERBREAD)
    fun clear() = sharedPref.clear()

    private fun MutableList<Cookie>.cleanup(url: HttpUrl, timestamp: Long) {
        removeAll { cookie ->
            cookie.expiresAt < getTimeMillis() || !cookie.matches(url)
        }
    }
}
