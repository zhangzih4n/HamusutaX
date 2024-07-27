@file:Suppress("unused", "FunctionName")
package hamusutax.wasmjs

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Date
 */
@JsName("Date")
external class JsDate : JsAny {
    /**
     * 一个整数，代表自 UTC 1970 年 1 月 1 日 00:00:00（ECMAScript 纪元，与 UNIX 纪元相同）以来的毫秒数，忽略闰秒。请记住，大多数 UNIX 时间戳函数只精确到最近的秒。
     */
    constructor(value: JsNumber)

    /**
     * 一个代表日期的字符串值，其格式由 Date.parse() 方法所识别。（ECMA262 规范规定了 ISO 8601 的简化版本，但其他格式可以由实现者定义，通常包括符合 IETF 的 RFC 2822 时间戳。）
     *
     * 当用 Date 构造函数（和 Date.parse，它们是等价的）解析日期字符串时，一定要确保输入符合 ISO 8601 格式（YYYY-MM-DDTHH:mm:ss.ssZ），
     * 其他格式的解析行为是实现定义的，可能无法在所有浏览器上运行。对 RFC 2822 格式字符串的支持只是惯例。如果要适应许多不同的格式，库可以提供帮助。
     *
     * 仅有日期的字符串（例如 "1970-01-01"）被视为 UTC，而日期时间的字符串（例如 "1970-01-01T12:00"）被视为本地时间。
     * 因此，我们也建议你确保这两种类型的输入格式是一致的。
     */
    constructor(dateString: String)

    /**
     * 一个现有的 Date 对象。这实际上是在现有的 Date 对象上复制了一个相同的日期和时间。这等同于 new Date(dateObject.valueOf())，除了不调用 valueOf() 方法。
     *
     * 当一个参数被传递给 Date() 构造函数时，Date 实例被特别处理。所有其他的值都被转换为原始值。如果结果是一个字符串，它将被解析为一个日期字符串。否则，产生的会被进一步强制转换为数值，并被视为时间戳。
     */
    constructor(dateObject: JsDate)

    /**
     * 给出至少一个年份和月份，这种形式的 `Date()` 返回一个 `Date` 对象，其组成部分的值（年、月、日、小时、分钟、秒和毫秒）都来自以下参数。任何缺失的字段都被赋予可能的最低值（`day` 为 `1`，其他所有组件为 `0`）。这些参数值都是根据当地的时区，而不是 UTC 来评估的。
     *
     * 如果任何参数超过其定义的范围，会发生“进位”。例如，如果传入一个大于 `11` 的 `monthIndex`，这些月将导致年的递增；如果传入一个大于 `59` 的 `minutes`，hours 将相应递增，等等。因此，`new Date(1990, 12, 1)` 将返回 1991 年 1 月 1 日；`new Date(2020, 5, 19, 25, 65)` 将返回 2020 年 6 月 20 日凌晨 2:05。
     *
     * 类似的，如果任何参数不足其定义的范围，会从高位“借位”。例如，`new Date(2020, 5, 0)` 将返回 2020 年 5 月 31 日。
     */
    constructor(year: JsNumber, monthIndex: JsNumber, day: JsNumber = definedExternally, hours: JsNumber = definedExternally, minutes: JsNumber = definedExternally, seconds: JsNumber = definedExternally, milliseconds: JsNumber = definedExternally)

    /**
     * 给出至少一个年份和月份，这种形式的 `Date()` 返回一个 `Date` 对象，其组成部分的值（年、月、日、小时、分钟、秒和毫秒）都来自以下参数。任何缺失的字段都被赋予可能的最低值（`day` 为 `1`，其他所有组件为 `0`）。这些参数值都是根据当地的时区，而不是 UTC 来评估的。
     *
     * 如果任何参数超过其定义的范围，会发生“进位”。例如，如果传入一个大于 `11` 的 `monthIndex`，这些月将导致年的递增；如果传入一个大于 `59` 的 `minutes`，hours 将相应递增，等等。因此，`new Date(1990, 12, 1)` 将返回 1991 年 1 月 1 日；`new Date(2020, 5, 19, 25, 65)` 将返回 2020 年 6 月 20 日凌晨 2:05。
     *
     * 类似的，如果任何参数不足其定义的范围，会从高位“借位”。例如，`new Date(2020, 5, 0)` 将返回 2020 年 5 月 31 日。
     */
    constructor(year: Int, monthIndex: Int, day: Int = definedExternally, hours: Int = definedExternally, minutes: Int = definedExternally, seconds: Int = definedExternally, milliseconds: Int = definedExternally)

    fun getDate(): JsNumber
    fun getDay(): JsNumber
    fun getFullYear(): JsNumber
    fun getHours(): JsNumber
    fun getMilliseconds(): JsNumber
    fun getMinutes(): JsNumber
    fun getMonth(): JsNumber
    fun getSeconds(): JsNumber
    fun getTime(): JsNumber
    fun getTimezoneOffset(): JsNumber
    fun getUTCDate(): JsNumber
    fun getUTCDay(): JsNumber
    fun getUTCFullYear(): JsNumber
    fun getUTCHours(): JsNumber
    fun getUTCMilliseconds(): JsNumber
    fun getUTCMinutes(): JsNumber
    fun getUTCMonth(): JsNumber
    fun getUTCSeconds(): JsNumber

    /**
     * 根据当地时间返回该日期的年份
     *
     * @return 一个整数，表示给定日期的年份（根据当地时间减去 1900）。如果日期无效，则返回 `NaN`。
     */
    @Deprecated(
        message = "由于 getYear() 不返回完整年份（“2000 年问题”），因此它已被弃用并已被 getFullYear() 方法取代。",
        replaceWith = ReplaceWith("getFullYear()")
    )
    fun getYear(): JsNumber

    fun setDate(dayValue: JsNumber)
    fun setDate(dayValue: Int)

    fun setFullYear(yearValue: JsNumber, monthValue: JsNumber = definedExternally, dayValue: JsNumber = definedExternally)
    fun setFullYear(yearValue: Int, monthValue: Int = definedExternally, dayValue: Int = definedExternally)

    fun setHours(hoursValue: JsNumber, minutesValue: JsNumber = definedExternally, secondsValue: JsNumber = definedExternally, msValue: JsNumber = definedExternally)
    fun setHours(hoursValue: Int, minutesValue: Int = definedExternally, secondsValue: Int = definedExternally, msValue: Int = definedExternally)

    fun setMilliseconds(millisecondsValue: JsNumber)
    fun setMilliseconds(millisecondsValue: Int)

    fun setMinutes(minutesValue: JsNumber, secondsValue: JsNumber = definedExternally, msValue: JsNumber = definedExternally)
    fun setMinutes(minutesValue: Int, secondsValue: Int = definedExternally, msValue: Int = definedExternally)

    fun setMonth(monthValue: JsNumber, dayValue: JsNumber = definedExternally)
    fun setMonth(monthValue: Int, dayValue: Int = definedExternally)

    fun setSeconds(secondsValue: JsNumber, msValue: JsNumber = definedExternally)
    fun setSeconds(secondsValue: Int, msValue: Int = definedExternally)

    fun setTime(timeValue: JsNumber)
    fun setTime(timeValue: Long)

    fun setUTCDate(dayValue: JsNumber)
    fun setUTCDate(dayValue: Int)

    fun setUTCFullYear(yearValue: JsNumber, monthValue: JsNumber = definedExternally, dayValue: JsNumber = definedExternally)
    fun setUTCFullYear(yearValue: Int, monthValue: Int = definedExternally, dayValue: Int = definedExternally)

    fun setUTCHours(hoursValue: JsNumber, minutesValue: JsNumber = definedExternally, secondsValue: JsNumber = definedExternally, msValue: JsNumber = definedExternally)
    fun setUTCHours(hoursValue: Int, minutesValue: Int = definedExternally, secondsValue: Int = definedExternally, msValue: Int = definedExternally)

    fun setUTCMilliseconds(millisecondsValue: JsNumber)
    fun setUTCMilliseconds(millisecondsValue: Int)

    fun setUTCMinutes(minutesValue: JsNumber, secondsValue: JsNumber = definedExternally, msValue: JsNumber = definedExternally)
    fun setUTCMinutes(minutesValue: Int, secondsValue: Int = definedExternally, msValue: Int = definedExternally)

    fun setUTCMonth(monthValue: JsNumber, dayValue: JsNumber = definedExternally)
    fun setUTCMonth(monthValue: Int, dayValue: Int = definedExternally)

    fun setUTCSeconds(secondsValue: JsNumber, msValue: JsNumber = definedExternally)
    fun setUTCSeconds(secondsValue: Int, msValue: Int = definedExternally)

    @Deprecated(
        message = "由于setYear() 并不设置完整年份（\"正是千年虫问题\"），本方法已经完全被 setFullYear() 方法所取代。",
        replaceWith = ReplaceWith("setFullYear()")
    )
    fun setYear(yearValue: JsNumber)
    @Deprecated(
        message = "由于setYear() 并不设置完整年份（\"正是千年虫问题\"），本方法已经完全被 setFullYear() 方法所取代。",
        replaceWith = ReplaceWith("setFullYear()")
    )
    fun setYear(yearValue: Int)

    fun toDateString(): JsString
    fun toISOString(): JsString
    fun toJSON(): JsString

    fun toLocaleDateString(locales: JsString = definedExternally, options: JsAny = definedExternally): JsString
    fun toLocaleDateString(locales: JsArray<JsString>, options: JsAny = definedExternally): JsString
    fun toLocaleDateString(locales: String, options: JsAny = definedExternally): JsString

    fun toLocaleString(locales: JsString = definedExternally, options: JsAny = definedExternally): JsString
    fun toLocaleString(locales: JsArray<JsString>, options: JsAny = definedExternally): JsString
    fun toLocaleString(locales: String, options: JsAny = definedExternally): JsString

    fun toLocaleTimeString(locales: JsString = definedExternally, options: JsAny = definedExternally): JsString
    fun toLocaleTimeString(locales: JsArray<JsString>, options: JsAny = definedExternally): JsString
    fun toLocaleTimeString(locales: String, options: JsAny = definedExternally): JsString

    @JsName("toString")
    fun toJsString(): JsString
    fun toTimeString(): JsString
    fun toUTCString(): JsString
    fun valueOf(): JsNumber

    companion object {
        fun now(): JsNumber

        /**
         * 解析一个表示某个日期的字符串，并返回从 1970-1-1 00:00:00 UTC 到该日期对象（该日期对象的 UTC 时间）的毫秒数，如果该字符串无法识别，或者一些情况下，包含了不合法的日期数值（如：2015-02-31），则返回值为 NaN。
         */
        fun parse(dateString: JsString): JsNumber

        /**
         * 解析一个表示某个日期的字符串，并返回从 1970-1-1 00:00:00 UTC 到该日期对象（该日期对象的 UTC 时间）的毫秒数，如果该字符串无法识别，或者一些情况下，包含了不合法的日期数值（如：2015-02-31），则返回值为 NaN。
         */
        fun parse(dateString: String): JsNumber

        fun UTC(): JsNumber
    }
}
