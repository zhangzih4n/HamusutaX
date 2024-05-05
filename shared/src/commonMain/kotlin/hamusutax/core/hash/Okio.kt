@file:Suppress("UNUSED")
package hamusutax.core.hash

import okio.ByteString
import okio.ByteString.Companion.encodeUtf8
import okio.ByteString.Companion.toByteString

fun String.md5() =
    encodeUtf8().md5().toByteArray()

fun ByteArray.md5() =
    toByteString().md5().toByteArray()

fun String.sha1() =
    encodeUtf8().sha1().toByteArray()

fun ByteArray.sha1() =
    toByteString().sha1().toByteArray()

fun String.sha256() =
    encodeUtf8().sha256().toByteArray()

fun ByteArray.sha256() =
    toByteString().sha256().toByteArray()

fun String.sha512() =
    encodeUtf8().sha512().toByteArray()

fun ByteArray.sha512() =
    toByteString().sha512().toByteArray()

fun String.hmacSha1(key: ByteString) =
    encodeUtf8().hmacSha1(key).toByteArray()

fun String.hmacSha1(key: ByteArray) =
    hmacSha1(key.toByteString())

fun ByteArray.hmacSha1(key: ByteString) =
    toByteString().hmacSha1(key).toByteArray()

fun ByteArray.hmacSha1(key: ByteArray) =
    hmacSha1(key.toByteString())

fun String.hmacSha256(key: ByteString) =
    encodeUtf8().hmacSha256(key).toByteArray()

fun String.hmacSha256(key: ByteArray) =
    hmacSha256(key.toByteString())

fun ByteArray.hmacSha256(key: ByteString) =
    toByteString().hmacSha256(key).toByteArray()

fun ByteArray.hmacSha256(key: ByteArray) =
    hmacSha256(key.toByteString())

fun String.hmacSha512(key: ByteString) =
    encodeUtf8().hmacSha512(key).toByteArray()

fun String.hmacSha512(key: ByteArray) =
    hmacSha512(key.toByteString())

fun ByteArray.hmacSha512(key: ByteString) =
    toByteString().hmacSha512(key).toByteArray()

fun ByteArray.hmacSha512(key: ByteArray) =
    hmacSha512(key.toByteString())
