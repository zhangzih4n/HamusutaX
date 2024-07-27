@file:Suppress("unused")
package hamusutax.ktor.http

import io.ktor.http.Headers
import io.ktor.http.HeadersBuilder
import io.ktor.http.Parameters
import io.ktor.http.ParametersBuilder
import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.takeFrom
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

inline fun buildHeaders(builderAction: HeadersBuilder.() -> Unit): Headers {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Headers.build(builderAction)
}

inline fun buildParameters(builderAction: ParametersBuilder.() -> Unit): Parameters {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Parameters.build(builderAction)
}

inline fun buildUrl(url: Url? = null, builderAction: URLBuilder.() -> Unit): Url {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return URLBuilder()
        .apply { url?.let { takeFrom(it) } }
        .apply(builderAction)
        .build()
}
