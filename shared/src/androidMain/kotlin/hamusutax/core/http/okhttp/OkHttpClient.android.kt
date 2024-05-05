@file:Suppress("UNUSED")
package hamusutax.core.http.okhttp

import okhttp3.OkHttpClient

actual fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder =
    ignoreAllSSLErrors()
