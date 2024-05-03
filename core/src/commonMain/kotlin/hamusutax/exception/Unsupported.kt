@file:Suppress("UNUSED")
package hamusutax.exception

fun unsupported(reason: String = "Not used."): Nothing =
    throw UnsupportedOperationException(reason)
