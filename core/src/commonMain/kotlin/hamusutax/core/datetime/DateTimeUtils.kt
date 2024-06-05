@file:Suppress("UNUSED")
package hamusutax.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.format
import kotlinx.datetime.toLocalDateTime

/**
 * 格式参考：https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */
fun Long.formatAsSeconds(pattern: String, timezone: TimeZone = TimeZone.currentSystemDefault()) =
    DateTimeComponents.Format { byUnicodePattern(pattern) }.format {
        setDateTime(Instant.fromEpochMilliseconds(this@formatAsSeconds).toLocalDateTime(timezone))
    }

private val secondsDefaultFormat = DateTimeComponents.Format { byUnicodePattern("yyyy-MM-dd HH:mm:ss") }
fun Long.formatAsSeconds(timezone: TimeZone = TimeZone.currentSystemDefault()) =
    secondsDefaultFormat.format {
        setDateTime(Instant.fromEpochSeconds(this@formatAsSeconds).toLocalDateTime(timezone))
    }

/**
 * 格式参考：https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */
fun Long.formatAsMillis(pattern: String, timezone: TimeZone = TimeZone.currentSystemDefault()) =
    DateTimeComponents.Format { byUnicodePattern(pattern) }.format {
        setDateTime(Instant.fromEpochMilliseconds(this@formatAsMillis).toLocalDateTime(timezone))
    }

private val millisecondsDefaultFormat = DateTimeComponents.Format { byUnicodePattern("yyyy-MM-dd HH:mm:ss.SSS") }
fun Long.formatAsMillis(timezone: TimeZone = TimeZone.currentSystemDefault()) =
    millisecondsDefaultFormat.format {
        setDateTime(Instant.fromEpochMilliseconds(this@formatAsMillis).toLocalDateTime(timezone))
    }
