@file:Suppress("UNUSED")
package hamusutax.jvm.collections

import hamusutax.core.collections.map.ByteArrayComparator
import java.util.SortedMap

fun <T> Map<ByteArray, T>.toSortedMap(): SortedMap<ByteArray, T> =
    toSortedMap(ByteArrayComparator)
