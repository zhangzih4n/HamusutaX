@file:Suppress("unused")
package hamusutax.okio.hash

import okio.ByteString.Companion.encodeUtf8
import okio.ByteString.Companion.toByteString

fun ByteArray.sha1() =
    toByteString().sha1().toByteArray()

fun ByteArray.sha256() =
    toByteString().sha256().toByteArray()

fun ByteArray.sha512() =
    toByteString().sha512().toByteArray()

fun String.sha1() =
    encodeUtf8().sha1().toByteArray()

fun String.sha256() =
    encodeUtf8().sha256().toByteArray()

fun String.sha512() =
    encodeUtf8().sha512().toByteArray()
