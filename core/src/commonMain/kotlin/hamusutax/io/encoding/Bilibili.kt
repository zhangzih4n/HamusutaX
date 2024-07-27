@file:Suppress("unused")
package hamusutax.io.encoding

import kotlin.math.pow

class Bilibili {
    companion object Default {
        private val table = "fZodR9XQDSUm21yCkr6zBqiveYah8bt4xsWpHnJE7jL5VG3guMTKNPAwcF".toCharArray()

        /**
         * 将哔哩哔哩视频 av 号转为 BV 号
         *
         * @param prefix 返回字符串是否以 `BV` 开头
         */
        fun encodeToBV(source: Long, prefix: Boolean = true): String {
            val bv = "BV1  4 1 7  ".toCharArray()
            intArrayOf(11, 10, 3, 8, 4, 6).forEachIndexed { index, value ->
                bv[value] = table[
                    (((source xor 177451812) + 8728348608) / 58.0.pow(index) % 58).toInt()
                ]
            }
            val result = bv.joinToString("")
            return if (prefix) result else result.substring(2)
        }
    }


    /**
     * 将哔哩哔哩视频 BV 号转为 av 号
     *
     * 字符串需要以 `BV` 开头
     */
    fun encodeToAV(source: String): Long {
        require(source.length > 2 && source.slice(0..1) == "BV")
        val result = intArrayOf(11, 10, 3, 8, 4, 6)
            .mapIndexed { index, value ->
                table.indexOf(source[value]) * 58.0.pow(index).toLong()
            }.sum()
        return (result - 8728348608) xor 177451812
    }
}
