@file:Suppress("unused")
package hamusutax.okio.hash

import okio.ByteString.Companion.encodeUtf8
import okio.ByteString.Companion.toByteString

fun String.md5() =
    encodeUtf8().md5().toByteArray()

fun ByteArray.md5() =
    toByteString().md5().toByteArray()
