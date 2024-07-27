@file:Suppress("unused")
package hamusutax.collections.list

fun <T> List<T>.subList(fromIndex: Int) =
    subList(fromIndex, size)
