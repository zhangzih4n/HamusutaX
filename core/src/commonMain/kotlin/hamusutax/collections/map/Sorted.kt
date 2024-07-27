package hamusutax.collections.map

/**
 * 尝试对 Map 进行排序。受限于不同实现，操作可能不生效
 */
fun <K, V, R : Comparable<R>> Map<K, V>.sortedBy(selector: (K, V) -> R?) =
    toList().sortedBy { selector(it.first, it.second) }.toMap()

/**
 * 尝试对 Map 进行排序。受限于不同实现，操作可能不生效
 */
fun <K : Comparable<K>> Map<K, *>.sorted() =
    toList().sortedBy { it.first }.toMap()

/**
 * 尝试对 Map 进行反转。受限于不同实现，操作可能不生效
 */
fun Map<*, *>.reversed() =
    toList().reversed().toMap()
