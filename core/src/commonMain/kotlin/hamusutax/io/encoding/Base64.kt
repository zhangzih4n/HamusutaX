@file:Suppress("unused")
package hamusutax.io.encoding

import kotlin.io.encoding.Base64

fun ByteArray.encodeToBase64(startIndex: Int = 0, endIndex: Int = size) =
    Base64.encode(this, startIndex, endIndex)

fun ByteArray.encodeToBase64UrlSafe(startIndex: Int = 0, endIndex: Int = size) =
    Base64.UrlSafe.encodeToByteArray(this, startIndex, endIndex)


fun CharSequence.decodeFromBase64(startIndex: Int = 0) =
    Base64.decode(toString().encodeToByteArray(), startIndex)

fun CharSequence.decodeFromBase64UrlSafe(startIndex: Int = 0) =
    Base64.UrlSafe.decode(toString().encodeToByteArray(), startIndex)


fun CharArray.decodeFromBase64(startIndex: Int = 0) =
    toString().decodeFromBase64(startIndex)

fun CharArray.decodeFromBase64UrlSafe(startIndex: Int = 0) =
    toString().decodeFromBase64UrlSafe(startIndex)
