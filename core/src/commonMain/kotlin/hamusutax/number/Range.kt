@file:Suppress("unused")
package hamusutax.number

/**
 * 将字符串解析为 [IntRange]，失败时抛出异常
 */
fun String.toIntRange(delimiter: String = "-"): IntRange {
    val splitTexts = split(delimiter)
    require(splitTexts.size == 2) { "Can't split string into two parts!" }
    val num1 = splitTexts[0].toIntOrNull()
        ?: throw IllegalArgumentException("The first part of the string cannot be converted to Int type!")
    val num2 = splitTexts[1].toIntOrNull()
        ?: throw IllegalArgumentException("The second part of the string cannot be converted to Int type!")
    return num1..num2
}

/**
 * 将字符串解析为 [IntRange]，失败则返回 `null`
 */
fun String.toIntRangeOrNull(delimiter: String = "-"): IntRange? {
    val runResult = runCatching { toIntRange(delimiter) }
    runResult.exceptionOrNull()?.let { exception ->
        if (exception is IllegalArgumentException) return null
        else throw exception
    }
    return runResult.getOrNull()
}

/**
 * 将字符串解析为 [LongRange]，失败时抛出异常
 */
fun String.toLongRange(delimiter: String = "-"): LongRange {
    val splitTexts = split(delimiter)
    require(splitTexts.size == 2) { "Can't split string into two parts!" }
    val num1 = splitTexts[0].toLongOrNull()
        ?: throw IllegalArgumentException("The first part of the string cannot be converted to Long type!")
    val num2 = splitTexts[1].toLongOrNull()
        ?: throw IllegalArgumentException("The second part of the string cannot be converted to Long type!")
    return num1..num2
}

/**
 * 将字符串解析为 [LongRange]，失败则返回 `null`
 */
fun String.toLongRangeOrNull(delimiter: String = "-") =
    try { toLongRange(delimiter) }
    catch (exception: IllegalArgumentException) { null }
