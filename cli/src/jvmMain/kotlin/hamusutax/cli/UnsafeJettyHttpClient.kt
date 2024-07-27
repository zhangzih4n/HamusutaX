package hamusutax.cli

import hamusutax.network.insecureSSLContext
import io.ktor.client.HttpClient
import io.ktor.client.engine.jetty.Jetty
import org.eclipse.jetty.util.ssl.SslContextFactory

/**
 * 未测试
 */
val UnsafeJettyHttpClient = HttpClient(Jetty) {
    engine {
        sslContextFactory = SslContextFactory.Client()
            .apply {
                sslContext = insecureSSLContext
            }
    }
}
