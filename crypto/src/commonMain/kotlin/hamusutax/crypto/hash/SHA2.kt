package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.hash.sha2.SHA224
import org.kotlincrypto.hash.sha2.SHA256
import org.kotlincrypto.hash.sha2.SHA384
import org.kotlincrypto.hash.sha2.SHA512
import org.kotlincrypto.hash.sha2.SHA512t

inline fun ByteArray.sha224() = digest(SHA224())

inline fun ByteArray.sha256() = digest(SHA256())

inline fun ByteArray.sha384() = digest(SHA384())

inline fun ByteArray.sha512() = digest(SHA512())

inline fun ByteArray.sha512t(t: Int) = digest(SHA512t(t))

inline fun ByteString.sha224() = digest(SHA224())

inline fun ByteString.sha256() = digest(SHA256())

inline fun ByteString.sha384() = digest(SHA384())

inline fun ByteString.sha512() = digest(SHA512())

inline fun ByteString.sha512t(t: Int) = digest(SHA512t(t))

inline fun String.sha224() = encodeToByteArray().sha224()

inline fun String.sha256() = encodeToByteArray().sha256()

inline fun String.sha512() = encodeToByteArray().sha512()

inline fun String.sha512t(t: Int) = encodeToByteArray().sha512t(t)
