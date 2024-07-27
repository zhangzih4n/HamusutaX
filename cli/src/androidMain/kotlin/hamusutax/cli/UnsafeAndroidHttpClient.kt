package hamusutax.cli

import hamusutax.network.insecureSSLManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android

val UnsafeAndroidHttpClient = HttpClient(Android) {
    engine {
        sslManager = insecureSSLManager
    }
}
