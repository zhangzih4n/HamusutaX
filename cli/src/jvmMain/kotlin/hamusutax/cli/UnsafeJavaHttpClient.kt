package hamusutax.cli

import hamusutax.network.insecureSSLContext
import io.ktor.client.HttpClient
import io.ktor.client.engine.java.Java

// 未测试
val UnsafeJavaHttpClient = HttpClient(Java) {
    engine {
        config {
            sslContext(insecureSSLContext)
        }
    }
}
