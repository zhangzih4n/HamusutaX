@file:Suppress("unused")
package hamusutax.collections.iterable

inline fun <T, R> Iterable<T>.mapCached(key: (T) -> Any? = { it }, transform: (T) -> R): List<R> {
    val caches = mutableMapOf<Int, R>()
    return map {
        caches.getOrPut(key = key(it).hashCode()) {
            transform(it)
        }
    }
}
