package hamusutax.network

import javax.net.ssl.HostnameVerifier
import javax.net.ssl.HttpsURLConnection

/**
 * ```kt
 * HttpClient(Android) {
 *     engine {
 *         sslManager = insecureSSLManager
 *     }
 * }
 * ```
 */
val insecureSSLManager = { httpsURLConnection: HttpsURLConnection ->
    httpsURLConnection.hostnameVerifier = HostnameVerifier { _, _ -> true }
    httpsURLConnection.sslSocketFactory = insecureSSLContext.socketFactory
}
