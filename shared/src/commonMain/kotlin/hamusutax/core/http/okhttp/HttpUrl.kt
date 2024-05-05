@file:Suppress("UNUSED")
package hamusutax.core.http.okhttp

import okhttp3.HttpUrl
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun buildHttpUrl(builderAction: HttpUrl.Builder.() -> Unit): HttpUrl {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return HttpUrl.Builder().apply(builderAction).build()
}

@OptIn(ExperimentalContracts::class)
inline fun buildHttpUrl(httpUrl: HttpUrl, builderAction: HttpUrl.Builder.() -> Unit): HttpUrl {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return httpUrl.newBuilder().apply(builderAction).build()
}

fun HttpUrl.Builder.addQueryParameters(params: Map<String, String?>): HttpUrl.Builder {
    params.forEach { (key, value) ->
        addQueryParameter(key, value)
    }
    return this
}

fun HttpUrl.Builder.addEncodedQueryParameters(params: Map<String, String?>): HttpUrl.Builder {
    params.forEach { (key, value) ->
        addEncodedQueryParameter(key, value)
    }
    return this
}

val HttpUrl.queryParameters: Map<String, String>
    get() = queryParameterNames.associateWith { queryParameter(it)!! }
