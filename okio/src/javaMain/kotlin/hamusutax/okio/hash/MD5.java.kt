@file:Suppress("unused")
package hamusutax.okio.hash

import java.nio.charset.Charset

fun String.md5(charset: Charset) =
    toByteArray(charset).md5()
