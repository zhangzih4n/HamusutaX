package hamusutax.tuple

inline fun <reified T> Triple<T, T, T>.toArray() =
    arrayOf(first, second, third)
