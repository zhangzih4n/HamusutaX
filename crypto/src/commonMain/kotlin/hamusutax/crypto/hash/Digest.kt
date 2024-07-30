package hamusutax.crypto.hash

import kotlinx.io.bytestring.ByteString
import org.kotlincrypto.core.digest.Digest

internal inline fun ByteArray.digest(md: Digest): ByteArray {
    md.update(this)
    return md.digest()
}

internal inline fun ByteString.digest(md: Digest) =
    toByteArray().digest(md)
