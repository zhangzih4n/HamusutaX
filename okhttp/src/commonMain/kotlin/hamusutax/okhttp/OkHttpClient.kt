@file:Suppress("UNUSED")
package hamusutax.okhttp

import okhttp3.OkHttpClient
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

inline fun buildOkHttpClient(builderAction: OkHttpClient.Builder.() -> Unit): OkHttpClient {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return OkHttpClient.Builder().apply(builderAction).build()
}

expect fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder
