package hamusutax.cli

import hamusutax.network.insecureSSLContext
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache.Apache

val UnsafeApacheHttpClient = HttpClient(Apache) {
    engine {
        sslContext = insecureSSLContext
    }
}
