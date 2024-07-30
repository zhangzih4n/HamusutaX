package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.hash.md.MD5

fun ByteArray.md5() = digest(MD5())

fun ByteString.md5() = digest(MD5())

fun String.md5() = encodeToByteArray().md5()
