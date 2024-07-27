package hamusutax.io.encoding

/**
 * ISO 7064:1983, MOD 11-2 校验码系统
 */
fun String.mod11_2(): Int {
    fun weight(n: Int) = (1 shl length - n) % 11
    return mapIndexed { index, char -> char.digitToInt() * weight(index) }
        .sum()
        .let { (12 - it % 11) % 11 }
}

fun String.isMod11_2(): Boolean {
    fun weight(n: Int) = (1 shl length - (n + 1)) % 11

    return mapIndexed { index, char -> char.digitToInt() * weight(index) }
        .sum() % 11 == 1
}
