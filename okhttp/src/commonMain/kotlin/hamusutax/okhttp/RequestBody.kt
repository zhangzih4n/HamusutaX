@file:Suppress("unused")
package hamusutax.okhttp

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import okio.Buffer

fun RequestBody.payload(): String {
    val buffer = Buffer()
    writeTo(buffer)
    return buffer.readUtf8()
}

fun String.toJsonRequestBody() =
    toRequestBody("application/json;charset=utf-8".toMediaType())
