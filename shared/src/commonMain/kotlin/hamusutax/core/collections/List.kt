@file:Suppress("UNUSED")
package hamusutax.core.collections

fun <T> List<T>.subList(fromIndex: Int) =
    subList(fromIndex, size)
