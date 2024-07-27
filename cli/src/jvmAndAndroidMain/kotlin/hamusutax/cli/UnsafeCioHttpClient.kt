package hamusutax.cli

import hamusutax.network.InsecureX509TrustManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO

val UnsafeCioHttpClient = HttpClient(CIO) {
    engine {
        https {
            trustManager = InsecureX509TrustManager
        }
    }
}
