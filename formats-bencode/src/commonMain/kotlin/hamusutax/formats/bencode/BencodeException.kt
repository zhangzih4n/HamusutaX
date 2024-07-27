@file:Suppress("unused")
package hamusutax.formats.bencode

import kotlinx.serialization.SerializationException

internal open class BencodeException(message: String) : SerializationException(message)

internal class BencodeDecodingException(message: String) : BencodeException(message)

internal fun BencodeDecodingException(offset: Int, message: String) =
    BencodeDecodingException(if (offset >= 0) "Unexpected Bencode token at offset $offset: $message" else message)

internal class BencodeEncodingException(message: String) : BencodeException(message)

internal fun BencodeDecodingException(offset: Int, message: String, input: CharSequence) =
    BencodeDecodingException(offset, "$message\nBencode input: ${input.minify(offset)}")

internal fun BencodeEncodingException(offset: Int, message: String) =
    BencodeEncodingException(if (offset >= 0) "Unexpected Bencode token at offset $offset: $message" else message)

internal fun CharSequence.minify(offset: Int = -1): CharSequence {
    if (length < 200) return this
    if (offset == -1) {
        val start = this.length - 60
        if (start <= 0) return this
        return "....." + substring(start)
    }

    val start = offset - 30
    val end = offset + 30
    val prefix = if (start <= 0) "" else "....."
    val suffix = if (end >= length) "" else "....."
    return prefix + substring(start.coerceAtLeast(0), end.coerceAtMost(length)) + suffix
}
