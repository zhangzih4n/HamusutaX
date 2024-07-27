package hamusutax.cli

import hamusutax.network.insecureSSLContext
import io.ktor.client.HttpClient
import io.ktor.client.engine.apache5.Apache5

val UnsafeApache5HttpClient = HttpClient(Apache5) {
    engine {
        sslContext = insecureSSLContext
    }
}
