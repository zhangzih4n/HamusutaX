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

fun ByteArray.keccak224() = digest(Keccak224())

fun ByteArray.keccak256() = digest(Keccak256())

fun ByteArray.keccak384() = digest(Keccak384())

fun ByteArray.keccak512() = digest(Keccak512())

fun ByteArray.sha3_224() = digest(SHA3_224())

fun ByteArray.sha3_256() = digest(SHA3_256())

fun ByteArray.sha3_384() = digest(SHA3_384())

fun ByteArray.sha3_512() = digest(SHA3_512())

fun ByteString.keccak224() = digest(Keccak224())

fun ByteString.keccak256() = digest(Keccak256())

fun ByteString.keccak384() = digest(Keccak384())

fun ByteString.keccak512() = digest(Keccak512())

fun ByteString.sha3_224() = digest(SHA3_224())

fun ByteString.sha3_256() = digest(SHA3_256())

fun ByteString.sha3_384() = digest(SHA3_384())

fun ByteString.sha3_512() = digest(SHA3_512())

fun String.keccak224() = encodeToByteArray().keccak224()

fun String.keccak256() = encodeToByteArray().keccak256()

fun String.keccak384() = encodeToByteArray().keccak384()

fun String.keccak512() = encodeToByteArray().keccak512()

fun String.sha3_224() = encodeToByteArray().sha3_224()

fun String.sha3_256() = encodeToByteArray().sha3_256()

fun String.sha3_384() = encodeToByteArray().sha3_384()

fun String.sha3_512() = encodeToByteArray().sha3_512()
