package hamusutax.network

import java.security.SecureRandom
import javax.net.ssl.SSLContext

/**
 * 用于 OkHttp 客户端，忽略证书错误
 *
 * ```kt
 * HttpClient(OkHttp) {
 *     engine {
 *         sslManager = insecureSSLManager
 *     }
 * }
 * ```
 */
val insecureSSLContext: SSLContext =
    SSLContext.getInstance("TLSv1.2")
        .apply {
            init(null, arrayOf(InsecureX509TrustManager), SecureRandom())
        }
