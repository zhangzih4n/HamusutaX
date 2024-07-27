@file:Suppress("FunctionName")
package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.hash.sha3.Keccak224
import org.kotlincrypto.hash.sha3.Keccak256
import org.kotlincrypto.hash.sha3.Keccak384
import org.kotlincrypto.hash.sha3.Keccak512
import org.kotlincrypto.hash.sha3.SHA3_224
import org.kotlincrypto.hash.sha3.SHA3_256
import org.kotlincrypto.hash.sha3.SHA3_384
import org.kotlincrypto.hash.sha3.SHA3_512

inline fun ByteArray.keccak224() = digest(Keccak224())

inline fun ByteArray.keccak256() = digest(Keccak256())

inline fun ByteArray.keccak384() = digest(Keccak384())

inline fun ByteArray.keccak512() = digest(Keccak512())

inline fun ByteArray.sha3_224() = digest(SHA3_224())

inline fun ByteArray.sha3_256() = digest(SHA3_256())

inline fun ByteArray.sha3_384() = digest(SHA3_384())

inline fun ByteArray.sha3_512() = digest(SHA3_512())

inline fun ByteString.keccak224() = digest(Keccak224())

inline fun ByteString.keccak256() = digest(Keccak256())

inline fun ByteString.keccak384() = digest(Keccak384())

inline fun ByteString.keccak512() = digest(Keccak512())

inline fun ByteString.sha3_224() = digest(SHA3_224())

inline fun ByteString.sha3_256() = digest(SHA3_256())

inline fun ByteString.sha3_384() = digest(SHA3_384())

inline fun ByteString.sha3_512() = digest(SHA3_512())

inline fun String.keccak224() = encodeToByteArray().keccak224()

inline fun String.keccak256() = encodeToByteArray().keccak256()

inline fun String.keccak384() = encodeToByteArray().keccak384()

inline fun String.keccak512() = encodeToByteArray().keccak512()

inline fun String.sha3_224() = encodeToByteArray().sha3_224()

inline fun String.sha3_256() = encodeToByteArray().sha3_256()

inline fun String.sha3_384() = encodeToByteArray().sha3_384()

inline fun String.sha3_512() = encodeToByteArray().sha3_512()
