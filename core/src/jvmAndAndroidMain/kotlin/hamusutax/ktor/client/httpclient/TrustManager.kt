package hamusutax.ktor.client.httpclient

import java.security.cert.X509Certificate
import javax.net.ssl.X509TrustManager

val ignoreAllSslErrorsTrustManager =
    @Suppress("CustomX509TrustManager")
    object : X509TrustManager {
        override fun getAcceptedIssuers(): Array<X509Certificate?> = arrayOf()
        @Suppress("TrustAllX509TrustManager")
        override fun checkClientTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
        @Suppress("TrustAllX509TrustManager")
        override fun checkServerTrusted(certs: Array<X509Certificate?>?, authType: String?) {}
    }
