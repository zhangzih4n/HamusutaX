@file:Suppress("UNUSED")
package hamusutax.okhttp.cookiejar

import hamusutax.core.datetime.getTimeMillis
import hamusutax.okhttp.fillDefaults
import kotlinx.atomicfu.AtomicLong
import kotlinx.atomicfu.atomic
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.sync.Mutex
import kotlinx.coroutines.sync.withLock
import kotlinx.serialization.Serializable
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * 存储于内存中的 CookieJar
 *
 * 可使用 [kotlinx.serialization] 进行持久化
 */
@Serializable(AcceptAllCookieJarSerializer::class)
open class AcceptAllCookieJar : CookieJar {
    override fun loadForRequest(url: HttpUrl): List<Cookie> =
        runBlocking { get(url) }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) =
        runBlocking {
            cookies.forEach { set(url, it) }
        }

    internal val container = mutableListOf<Cookie>()
    private val oldestCookie: AtomicLong = atomic(0L)
    private val mutex = Mutex()

    suspend fun get(requestUrl: HttpUrl) = mutex.withLock {
        val now = getTimeMillis()
        if (now >= oldestCookie.value) cleanup(now)
        return@withLock container.filter { it.matches(requestUrl) }
    }

    suspend fun set(requestUrl: HttpUrl, cookie: Cookie): Unit = mutex.withLock {
        with(cookie) {
            if (name.isBlank()) return@withLock
        }

        container.removeAll { it.name == cookie.name && it.matches(requestUrl) }
        container.add(cookie.fillDefaults(requestUrl))
        cookie.expiresAt.let { expiresAt ->
            if (oldestCookie.value > expiresAt) {
                oldestCookie.value = expiresAt
            }
        }
    }

    private fun cleanup(timestamp: Long) {
        container.removeAll { cookie ->
            cookie.expiresAt < timestamp
        }
        oldestCookie.value = container.minOf { it.expiresAt }
    }
}
