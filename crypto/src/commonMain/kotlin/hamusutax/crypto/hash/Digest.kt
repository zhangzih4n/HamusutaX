package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.core.digest.Digest

inline fun ByteArray.digest(md: Digest): ByteArray {
    md.update(this)
    return md.digest()
}

inline fun ByteString.digest(md: Digest): ByteArray {
    md.update(toByteArray())
    return md.digest()
}
