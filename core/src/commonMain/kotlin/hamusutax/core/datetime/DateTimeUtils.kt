@file:Suppress("UNUSED")
package hamusutax.core.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.format
import kotlinx.datetime.toLocalDateTime

val Long.epochSeconds get() =
    Instant.fromEpochSeconds(this)

val Long.epochMilliseconds get() =
    Instant.fromEpochMilliseconds(this)

/**
 * 格式参考：https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */
fun Long.secondsFormat(pattern: String, timezone: TimeZone = TimeZone.currentSystemDefault()) =
    DateTimeComponents.Format { byUnicodePattern(pattern) }.format {
        setDateTime(Instant.fromEpochMilliseconds(this@secondsFormat).toLocalDateTime(timezone))
    }

private val secondsDefaultFormat = DateTimeComponents.Format { byUnicodePattern("yyyy-MM-dd HH:mm:ss") }
fun Long.secondsFormat(timezone: TimeZone = TimeZone.currentSystemDefault()) =
    secondsDefaultFormat.format {
        setDateTime(Instant.fromEpochSeconds(this@secondsFormat).toLocalDateTime(timezone))
    }

/**
 * 格式参考：https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 */
fun Long.millisecondsFormat(pattern: String, timezone: TimeZone = TimeZone.currentSystemDefault()) =
    DateTimeComponents.Format { byUnicodePattern(pattern) }.format {
        setDateTime(Instant.fromEpochMilliseconds(this@millisecondsFormat).toLocalDateTime(timezone))
    }

private val millisecondsDefaultFormat = DateTimeComponents.Format { byUnicodePattern("yyyy-MM-dd HH:mm:ss.SSS") }
fun Long.millisecondsFormat(timezone: TimeZone = TimeZone.currentSystemDefault()) =
    millisecondsDefaultFormat.format {
        setDateTime(Instant.fromEpochMilliseconds(this@millisecondsFormat).toLocalDateTime(timezone))
    }
