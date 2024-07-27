@file:Suppress("unused")
package hamusutax.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DayOfWeekNames
import kotlinx.datetime.format.MonthNames
import kotlinx.datetime.format.char
import kotlinx.datetime.format.format
import kotlinx.datetime.toLocalDateTime

private val cookieExpiresFormat = DateTimeComponents.Format {
    dayOfWeek(DayOfWeekNames.ENGLISH_ABBREVIATED)
    chars(", ")
    dayOfMonth()
    char('-')
    monthName(MonthNames.ENGLISH_ABBREVIATED)
    char('-')
    year()
    char(' ')
    hour()
    char(':')
    minute()
    char(':')
    second()
    chars(" GMT")
}

fun Instant.toCookieExpires() = cookieExpiresFormat.format {
    setDateTime(toLocalDateTime(TimeZone.UTC))
}
