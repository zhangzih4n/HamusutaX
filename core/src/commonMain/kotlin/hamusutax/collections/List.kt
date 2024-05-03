@file:Suppress("UNUSED")
package hamusutax.collections

fun <T> List<T>.subList(fromIndex: Int) =
    subList(fromIndex, size)
