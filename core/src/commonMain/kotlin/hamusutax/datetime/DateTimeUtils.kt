@file:Suppress("unused")
package hamusutax.datetime

import kotlinx.datetime.Instant
import kotlinx.datetime.TimeZone
import kotlinx.datetime.UtcOffset
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.format
import kotlinx.datetime.offsetAt

/**
 * 注意会使用 `TimeZone.offsetAt(Instant)` 确定时间偏移量
 */
fun Instant.format(
    format: DateTimeFormat<DateTimeComponents>,
    timezone: TimeZone
): String {
    val instant = this
    return format.format {
        setDateTimeOffset(instant, timezone.offsetAt(instant))
    }
}

/**
 * 格式参考：https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 *
 * 常用 Pattern：`yyyy-MM-dd HH:mm:ss`、`yyyy-MM-dd HH:mm:ss.SSS`
 */
fun Instant.formatByUnicodePattern(
    pattern: String,
    offset: UtcOffset = UtcOffset.ZERO
): String {
    val instant = this
    return DateTimeComponents.Format { byUnicodePattern(pattern) }
        .format {
            setDateTimeOffset(instant, offset)
        }
}

/**
 * 格式参考：https://docs.oracle.com/javase/8/docs/api/java/time/format/DateTimeFormatter.html
 *
 * 常用 Pattern：`yyyy-MM-dd HH:mm:ss`、`yyyy-MM-dd HH:mm:ss.SSS`
 *
 * 注意会使用 `TimeZone.offsetAt(Instant)` 确定时间偏移量
 */
fun Instant.formatByUnicodePattern(
    pattern: String,
    timezone: TimeZone
): String {
    val instant = this
    return DateTimeComponents.Format { byUnicodePattern(pattern) }
        .format {
            setDateTimeOffset(instant, timezone.offsetAt(instant))
        }
}
