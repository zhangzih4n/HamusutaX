package hamusutax.tuple

inline fun <reified T> Pair<T, T>.toArray() =
    arrayOf(first, second)
