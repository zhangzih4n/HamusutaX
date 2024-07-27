@file:Suppress("unused")
package hamusutax.okio.hash

import okio.ByteString
import okio.ByteString.Companion.encodeUtf8
import okio.ByteString.Companion.toByteString

fun ByteArray.hmacSha1(key: ByteString) =
    toByteString().hmacSha1(key).toByteArray()

fun ByteArray.hmacSha256(key: ByteString) =
    toByteString().hmacSha256(key).toByteArray()

fun ByteArray.hmacSha512(key: ByteString) =
    toByteString().hmacSha512(key).toByteArray()

fun ByteArray.hmacSha1(key: ByteArray) =
    hmacSha1(key.toByteString())

fun ByteArray.hmacSha256(key: ByteArray) =
    hmacSha256(key.toByteString())

fun ByteArray.hmacSha512(key: ByteArray) =
    hmacSha512(key.toByteString())

fun String.hmacSha1(key: ByteString) =
    encodeUtf8().hmacSha1(key).toByteArray()

fun String.hmacSha256(key: ByteString) =
    encodeUtf8().hmacSha256(key).toByteArray()

fun String.hmacSha512(key: ByteString) =
    encodeUtf8().hmacSha512(key).toByteArray()

fun String.hmacSha1(key: ByteArray) =
    hmacSha1(key.toByteString())

fun String.hmacSha256(key: ByteArray) =
    hmacSha256(key.toByteString())

fun String.hmacSha512(key: ByteArray) =
    hmacSha512(key.toByteString())
