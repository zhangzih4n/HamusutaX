@file:Suppress("UNUSED")
package hamusutax.core.io.hash

import java.nio.charset.Charset

fun String.md5(charset: Charset) =
    toByteArray(charset).md5()
