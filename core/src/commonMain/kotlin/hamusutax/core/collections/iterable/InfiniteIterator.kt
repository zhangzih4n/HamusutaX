package hamusutax.core.collections.iterable

fun <T> Iterable<T>.infiniteIterator() = iterator {
    val iter = iterator()
    val history = mutableListOf<T>()
    while (true) {
        if (iter.hasNext()) {
            history.add(iter.next())
            yield(history.last())
        } else {
            if (history.isEmpty())
                throw NoSuchElementException("The list is empty.")
            yieldAll(history)
        }
    }
}
