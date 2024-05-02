@file:Suppress("UNUSED")
package hamusutax.okhttp

import okhttp3.OkHttpClient
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

@OptIn(ExperimentalContracts::class)
inline fun buildOkHttpClient(builderAction: OkHttpClient.Builder.() -> Unit): OkHttpClient {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return OkHttpClient.Builder().apply(builderAction).build()
}
