@file:Suppress("UNUSED")
package hamusutax.core.http.okhttp

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer
import java.nio.charset.Charset

fun RequestBody.payload(charset: Charset = Charsets.UTF_8): String {
    val buffer = Buffer()
    writeTo(buffer)
    return buffer.readString(charset)
}

fun String.toJsonRequestBody() =
    toRequestBody("application/json;charset=utf-8".toMediaType())
