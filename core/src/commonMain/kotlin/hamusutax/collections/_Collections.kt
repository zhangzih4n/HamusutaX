package hamusutax.collections

import kotlin.jvm.JvmName

@JvmName("sumOfFloat")
inline fun <T> Iterable<T>.sumOf(selector: (T) -> Float): Float {
    var sum = 0F
    for (element in this) {
        sum += selector(element)
    }
    return sum
}
