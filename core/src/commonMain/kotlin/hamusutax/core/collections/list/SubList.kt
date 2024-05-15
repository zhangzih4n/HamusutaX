@file:Suppress("UNUSED")
package hamusutax.core.collections.list

fun <T> List<T>.subList(fromIndex: Int) =
    subList(fromIndex, size)
