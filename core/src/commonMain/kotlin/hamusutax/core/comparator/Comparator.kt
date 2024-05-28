@file:Suppress("UNUSED")
package hamusutax.core.comparator

val ByteArrayComparator = Comparator<ByteArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val ShortArrayComparator = Comparator<ShortArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val IntArrayComparator = Comparator<IntArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val LongArrayComparator = Comparator<LongArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val CharArrayComparator = Comparator<CharArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val ByteListComparator = Comparator<List<Byte>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val ShortListComparator = Comparator<List<Short>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val IntListComparator = Comparator<List<Int>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val LongListComparator = Comparator<List<Long>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val FloatListComparator = Comparator<List<Float>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val DoubleListComparator = Comparator<List<Double>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val CharListComparator = Comparator<List<Char>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val StringListComparator = Comparator<List<String>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}


@OptIn(ExperimentalUnsignedTypes::class)
val UByteArrayComparator = Comparator<UByteArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

@OptIn(ExperimentalUnsignedTypes::class)
val UShortArrayComparator = Comparator<UShortArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

@OptIn(ExperimentalUnsignedTypes::class)
val UIntArrayComparator = Comparator<UIntArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

@OptIn(ExperimentalUnsignedTypes::class)
val ULongArrayComparator = Comparator<ULongArray> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val UByteListComparator = Comparator<List<UByte>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val UShortListComparator = Comparator<List<UShort>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val UIntListComparator = Comparator<List<UInt>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}

val ULongListComparator = Comparator<List<ULong>> { a, b ->
    repeat(minOf(a.size, b.size)) { index ->
        val compare = a[index].compareTo(b[index])
        if (compare != 0)
            return@Comparator compare
    }
    return@Comparator a.size compareTo b.size
}
