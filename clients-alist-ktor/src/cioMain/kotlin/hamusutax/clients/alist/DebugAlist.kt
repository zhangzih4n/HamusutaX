package hamusutax.clients.alist

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.append
import io.ktor.serialization.kotlinx.json.json

fun getDebugAlist(
    client: HttpClient = HttpClient(CIO) {
        defaultRequest {
            headers {
                append(HttpHeaders.Accept, ContentType.Application.Json)
                append(HttpHeaders.ContentType, ContentType.Application.Json)
            }
        }
        install(ContentNegotiation) {
            json()
        }
        install(HttpCookies)
        install(UserAgent) {
            agent = "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/124.0.0.0 Safari/537.36"
        }
    },
    baseUrl: String = "http://127.0.0.1:5244"
) = Alist(client, baseUrl)
