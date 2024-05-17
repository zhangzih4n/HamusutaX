@file:Suppress("UNUSED")
package hamusutax.okhttp

import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

private val EmptyHeaders = emptyMap<String, String>().toHeaders()

fun emptyHeaders() = EmptyHeaders

inline fun buildHeaders(builderAction: Headers.Builder.() -> Unit): Headers {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Headers.Builder().apply(builderAction).build()
}
