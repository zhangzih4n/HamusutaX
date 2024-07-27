package hamusutax.cliApp

import hamusutax.network.InsecureX509TrustManager
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import java.net.ProxySelector
import java.net.URI

val httpClientWithProxy = HttpClient(CIO) {
    engine {
        https {
            trustManager = InsecureX509TrustManager
        }
        proxy = run {
            System.setProperty("java.net.useSystemProxies", "true")
            ProxySelector.getDefault()
                .select(URI("https://www.google.com/")).firstOrNull()
        }
    }
}
