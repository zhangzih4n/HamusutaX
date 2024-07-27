@file:Suppress("unused")
package hamusutax.ktor.client.request

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.HttpRequestData
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildHttpRequestData(builderAction: HttpRequestBuilder.() -> Unit): HttpRequestData {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return HttpRequestBuilder().apply(builderAction).build()
}
