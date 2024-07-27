@file:Suppress("unused")
package hamusutax.io.bytestring

import kotlinx.io.bytestring.ByteString

fun ByteArray.toByteString() =
    ByteString(this)

fun byteStringOf(vararg elements: Byte) =
    ByteString(byteArrayOf(*elements))
