package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.hash.md.MD5

inline fun ByteArray.md5() = digest(MD5())

inline fun ByteString.md5() = digest(MD5())

inline fun String.md5() = encodeToByteArray().md5()
