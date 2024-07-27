package hamusutax.datetime

import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.Month
import kotlinx.datetime.plus
import kotlinx.datetime.until

/**
 * 获取某年某月共有几天
 */
fun monthDays(year: Int, month: Month): Int {
    val start = LocalDate(year, month, 1)
    val end = start.plus(1, DateTimeUnit.MONTH)
    return start.until(end, DateTimeUnit.DAY)
}
