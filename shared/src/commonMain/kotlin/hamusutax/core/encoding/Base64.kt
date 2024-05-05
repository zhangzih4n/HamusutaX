@file:Suppress("UNUSED")
package hamusutax.core.encoding

import okio.ByteString.Companion.decodeBase64
import okio.ByteString.Companion.toByteString

fun ByteArray.encodeToBase64() =
    toByteString().base64()

fun String.decodeFromBase64() =
    decodeBase64()!!.toByteArray()
