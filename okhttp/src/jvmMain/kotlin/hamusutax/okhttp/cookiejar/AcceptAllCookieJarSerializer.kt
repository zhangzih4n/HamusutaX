package hamusutax.okhttp.cookiejar

import hamusutax.serialization.decodeSerializableValue
import hamusutax.serialization.encodeSerializableValue
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.STRING
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import okhttp3.Cookie
import okhttp3.HttpUrl.Companion.toHttpUrl

object AcceptAllCookieJarSerializer: KSerializer<AcceptAllCookieJar> {
    override val descriptor =
        PrimitiveSerialDescriptor("AcceptAllCookieJar", STRING)

    override fun deserialize(decoder: Decoder): AcceptAllCookieJar {
        val cookieStrings = decoder.decodeSerializableValue<Map<String, List<String>>>()
        return AcceptAllCookieJar().apply {
            cookieStrings.mapNotNull { (domainString, cookieStrings) ->
                val httpUrl = "https://$domainString".toHttpUrl()
                val cookies = cookieStrings.mapNotNull { Cookie.parse(httpUrl, it) }
                if (cookies.isNotEmpty()) httpUrl to cookies else null
            }.forEach { (url, cookies) ->
                saveFromResponse(url, cookies)
            }
        }
    }

    override fun serialize(encoder: Encoder, value: AcceptAllCookieJar) {
        val map = value.container.groupBy { it.domain }
            .mapValues {
                it.value.map { it.toString() }
            }
        encoder.encodeSerializableValue(map)
    }
}
