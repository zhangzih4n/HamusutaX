@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.FormBody
import okhttp3.Request
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

private val EmptyRequest = FormBody.Builder().build()

fun emptyRequest() = EmptyRequest

inline fun buildRequest(builderAction: Request.Builder.() -> Unit): Request {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return Request.Builder().apply(builderAction).build()
}
