@file:Suppress("unused", "FunctionName")
package hamusutax.js

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Date
 */
@JsName("Date")
external class JsDate {
    constructor(value: dynamic)
    constructor(dateString: String)
    constructor(year: Int, monthIndex: Int, day: Int = definedExternally, hours: Int = definedExternally, minutes: Int = definedExternally, seconds: Int = definedExternally, milliseconds: Int = definedExternally)

    fun getDate(): Int
    fun getDay(): Int
    fun getFullYear(): Int
    fun getHours(): Int
    fun getMilliseconds(): Int
    fun getMinutes(): Int
    fun getMonth(): Int
    fun getSeconds(): Int
    fun getTime(): Long
    fun getTimezoneOffset(): Int
    fun getUTCDate(): Int
    fun getUTCDay(): Int
    fun getUTCFullYear(): Int
    fun getUTCHours(): Int
    fun getUTCMilliseconds(): Int
    fun getUTCMinutes(): Int
    fun getUTCMonth(): Int
    fun getUTCSeconds(): Int
    @Deprecated(
        message = "由于 getYear() 不返回完整年份导致的千年虫问题，它已被 getFullYear() 方法取代。",
        replaceWith = ReplaceWith("getFullYear()")
    )
    fun getYear(): Int
    fun setDate(dayValue: Int)
    fun setFullYear(yearValue: Int, monthValue: Int = definedExternally, dayValue: Int = definedExternally)
    fun setHours(hoursValue: Int, minutesValue: Int = definedExternally, secondsValue: Int = definedExternally, msValue: Int = definedExternally)
    fun setMilliseconds(millisecondsValue: Int)
    fun setMinutes(minutesValue: Int, secondsValue: Int = definedExternally, msValue: Int = definedExternally)
    fun setMonth(monthValue: Int, dayValue: Int = definedExternally)
    fun setSeconds(secondsValue: Int, msValue: Int = definedExternally)
    fun setTime(timeValue: Long)
    fun setUTCDate(dayValue: Int)
    fun setUTCFullYear(yearValue: Int, monthValue: Int = definedExternally, dayValue: Int = definedExternally)
    fun setUTCHours(hoursValue: Int, minutesValue: Int = definedExternally, secondsValue: Int = definedExternally, msValue: Int = definedExternally)
    fun setUTCMilliseconds(millisecondsValue: Int)
    fun setUTCMinutes(minutesValue: Int, secondsValue: Int = definedExternally, msValue: Int = definedExternally)
    fun setUTCMonth(monthValue: Int, dayValue: Int = definedExternally)
    fun setUTCSeconds(secondsValue: Int, msValue: Int = definedExternally)
    @Deprecated(
        message = "由于 getYear() 不返回完整年份导致的千年虫问题，它已被 getFullYear() 方法取代。",
        replaceWith = ReplaceWith("setFullYear()")
    )
    fun setYear(yearValue: Int)
    fun toDateString(): String
    fun toISOString(): String
    fun toJSON(): String

    fun toLocaleDateString(locales: String = definedExternally, options: dynamic = definedExternally): String
    fun toLocaleDateString(locales: Array<String>, options: dynamic = definedExternally): String

    fun toLocaleString(locales: String = definedExternally, options: dynamic = definedExternally): String
    fun toLocaleString(locales: Array<String>, options: dynamic = definedExternally): String

    fun toLocaleTimeString(locales: String = definedExternally, options: dynamic = definedExternally): String
    fun toLocaleTimeString(locales: Array<String>, options: dynamic = definedExternally): String

    @JsName("toString")
    fun toJsString(): String
    fun toTimeString(): String
    fun toUTCString(): String
    fun valueOf(): Long

    companion object {
        fun now(): Long
        fun parse(dateString: String): Long
        fun UTC(): Long
    }
}
