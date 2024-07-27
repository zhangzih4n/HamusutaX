package hamusutax.network

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

/**
 * ```kt
 * HttpClient(CIO) {
 *     engine {
 *         https {
 *             trustManager = InsecureX509TrustManager
 *         }
 *     }
 * }
 * ```
 */
@Suppress("CustomX509TrustManager")
object InsecureX509TrustManager : X509TrustManager {
    override fun getAcceptedIssuers() = emptyArray<X509Certificate>()
    @Suppress("TrustAllX509TrustManager")
    override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) = Unit
    @Suppress("TrustAllX509TrustManager")
    override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) = Unit
}
