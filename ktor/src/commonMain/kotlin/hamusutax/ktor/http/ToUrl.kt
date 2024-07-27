package hamusutax.ktor.http

import io.ktor.http.URLBuilder
import io.ktor.http.Url

inline fun String.toUrl() = Url(this)
inline fun String.toURLBuilder() = URLBuilder(this)
inline fun Url.toURLBuilder() = URLBuilder(this)
