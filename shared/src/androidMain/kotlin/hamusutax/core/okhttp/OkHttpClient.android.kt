@file:Suppress("UNUSED")
package hamusutax.core.okhttp

import okhttp3.OkHttpClient

actual fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder =
    ignoreAllSSLErrors()
