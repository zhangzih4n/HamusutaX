@file:Suppress("unused")
package hamusutax.okhttp

import hamusutax.network.InsecureX509TrustManager
import hamusutax.network.insecureSSLContext
import okhttp3.OkHttpClient
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

inline fun buildOkHttpClient(builderAction: OkHttpClient.Builder.() -> Unit): OkHttpClient {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return OkHttpClient.Builder().apply(builderAction).build()
}

fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder {
    sslSocketFactory(insecureSSLContext.socketFactory, InsecureX509TrustManager)
    hostnameVerifier { _, _ -> true }
    return this
}
