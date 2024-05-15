@file:Suppress("UNUSED")
package hamusutax.okhttp

import okhttp3.OkHttpClient

actual fun OkHttpClient.Builder.ignoreAllSSLErrors(): OkHttpClient.Builder =
    ignoreAllSSLErrors()
