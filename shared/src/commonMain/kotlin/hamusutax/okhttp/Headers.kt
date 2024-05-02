@file:Suppress("UNUSED")
package hamusutax.okhttp

import okhttp3.Headers
import okhttp3.Headers.Companion.toHeaders
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

private val EmptyHeaders = emptyMap<String, String>().toHeaders()

fun emptyHeaders() = EmptyHeaders

@OptIn(ExperimentalContracts::class)
inline fun buildHeaders(builderAction: Headers.Builder.() -> Unit): Headers {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Headers.Builder().apply(builderAction).build()
}
