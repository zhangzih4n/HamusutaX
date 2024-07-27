@file:Suppress("unused")
package hamusutax.collections

import hamusutax.comparator.ByteArrayComparator
import java.util.SortedMap

fun <T> Map<ByteArray, T>.toSortedMap(): SortedMap<ByteArray, T> =
    toSortedMap(ByteArrayComparator)
