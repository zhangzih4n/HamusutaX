@file:Suppress("UNUSED")
package hamusutax.tuple

data class Nonuple<out A, out B, out C, out D, out E, out F, out G, out H, out I>(
    val first: A,
    val second: B,
    val third: C,
    val fourth: D,
    val fifth: E,
    val sixth: F,
    val seventh: G,
    val eighth: H,
    val ninth: I
) {
    override fun toString() =
        "($first, $second, $third, $fourth, $fifth, $sixth, $seventh, $eighth, $ninth)"
}

fun <T> Nonuple<T, T, T, T, T, T, T, T, T>.toList() =
    listOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)

inline fun <reified T> Nonuple<T, T, T, T, T, T, T, T, T>.toArray() =
    arrayOf(first, second, third, fourth, fifth, sixth, seventh, eighth, ninth)
