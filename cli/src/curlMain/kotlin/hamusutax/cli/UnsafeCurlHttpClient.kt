package hamusutax.cli

import io.ktor.client.HttpClient
import io.ktor.client.engine.curl.Curl

/**
 * 未测试
 */
val UnsafeCurlHttpClient = HttpClient(Curl) {
    engine {
        sslVerify = false
    }
}
