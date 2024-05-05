@file:Suppress("UNUSED")
package hamusutax.core.io

import kotlinx.io.bytestring.ByteString

fun ByteArray.toByteString() =
    ByteString(this)

fun byteStringOf(vararg elements: Byte) =
    byteArrayOf(*elements).toByteString()
