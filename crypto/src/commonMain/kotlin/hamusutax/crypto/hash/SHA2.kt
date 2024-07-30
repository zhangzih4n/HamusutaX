package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.hash.sha2.SHA224
import org.kotlincrypto.hash.sha2.SHA256
import org.kotlincrypto.hash.sha2.SHA384
import org.kotlincrypto.hash.sha2.SHA512
import org.kotlincrypto.hash.sha2.SHA512t

fun ByteArray.sha224() = digest(SHA224())

fun ByteArray.sha256() = digest(SHA256())

fun ByteArray.sha384() = digest(SHA384())

fun ByteArray.sha512() = digest(SHA512())

fun ByteArray.sha512t(t: Int) = digest(SHA512t(t))

fun ByteString.sha224() = digest(SHA224())

fun ByteString.sha256() = digest(SHA256())

fun ByteString.sha384() = digest(SHA384())

fun ByteString.sha512() = digest(SHA512())

fun ByteString.sha512t(t: Int) = digest(SHA512t(t))

fun String.sha224() = encodeToByteArray().sha224()

fun String.sha256() = encodeToByteArray().sha256()

fun String.sha512() = encodeToByteArray().sha512()

fun String.sha512t(t: Int) = encodeToByteArray().sha512t(t)
