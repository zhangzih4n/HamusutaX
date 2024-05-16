@file:Suppress("UNUSED")
package hamusutax.core.collections.map

val ByteArrayComparator = Comparator<ByteArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}
