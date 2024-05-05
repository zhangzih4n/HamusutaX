@file:Suppress("UNUSED")
package hamusutax.core.exception

fun unsupported(reason: String = "Not used."): Nothing =
    throw UnsupportedOperationException(reason)
