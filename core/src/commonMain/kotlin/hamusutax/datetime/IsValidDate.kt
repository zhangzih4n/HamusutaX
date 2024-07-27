@file:Suppress("unused")
package hamusutax.datetime

private val dayOfMonthArray = arrayOf(31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)

fun isValidDate(monthNumber: Int, dayOfMonth: Int): Boolean {
    if (monthNumber !in 0..12) return false
    if (dayOfMonth < 1 || dayOfMonth > dayOfMonthArray[monthNumber]) return false
    return true
}

fun isValidDate(year: Int, monthNumber: Int, dayOfMonth: Int): Boolean {
    if (!isValidDate(monthNumber, dayOfMonth)) return false

    if (monthNumber == 2) {
        val isLeapYear = year % 4 == 0 && year % 400 != 0
        if (!isLeapYear && dayOfMonth > 28) return false
    }
    return true
}
