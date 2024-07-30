package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.hash.sha1.SHA1

fun ByteArray.sha1() = digest(SHA1())

fun ByteString.sha1() = digest(SHA1())

fun String.sha1() = encodeToByteArray().sha1()
