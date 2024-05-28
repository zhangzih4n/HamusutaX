@file:Suppress("UNUSED")
package hamusutax.core.io.hash

import okio.ByteString
import java.nio.charset.Charset

fun String.hmacSha1(key: ByteString, charset: Charset) =
    toByteArray(charset).hmacSha1(key)

fun String.hmacSha256(key: ByteString, charset: Charset) =
    toByteArray(charset).hmacSha256(key)

fun String.hmacSha512(key: ByteString, charset: Charset) =
    toByteArray(charset).hmacSha512(key)


fun String.hmacSha1(key: ByteArray, charset: Charset) =
    toByteArray(charset).hmacSha1(key)

fun String.hmacSha256(key: ByteArray, charset: Charset) =
    toByteArray(charset).hmacSha256(key)

fun String.hmacSha512(key: ByteArray, charset: Charset) =
    toByteArray(charset).hmacSha512(key)
