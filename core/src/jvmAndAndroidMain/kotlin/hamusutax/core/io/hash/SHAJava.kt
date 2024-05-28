@file:Suppress("UNUSED")
package hamusutax.core.io.hash

import java.nio.charset.Charset

fun String.sha1(charset: Charset) =
    toByteArray(charset).sha1()

fun String.sha256(charset: Charset) =
    toByteArray(charset).sha256()

fun String.sha512(charset: Charset) =
    toByteArray(charset).sha512()
