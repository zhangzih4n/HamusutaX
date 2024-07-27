@file:Suppress("unused")
package hamusutax.io.encoding

/**
 * 将一个 Unicode 字符转换为代理对
 * @return 代理对的高位与低位
 */
fun Int.toSurrogatePair(): Pair<Int, Int> {
    val codePoint = this - 0x10000
    require(codePoint >= 0)
    val highSurrogate = codePoint / 0x400 + 0xD800
    val lowSurrogate = codePoint % 0x400 + 0xDC00
    return highSurrogate to lowSurrogate
}
