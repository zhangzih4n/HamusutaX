@file:Suppress("unused")
package hamusutax.wasmjs

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/BigInt
 */
@JsName("Number")
external class JsNumberObject : JsAny {
    fun toExponential(fractionDigits: JsNumber = definedExternally): JsString
    fun toExponential(fractionDigits: Int): JsString

    /**
     * 小数点后的位数。应该是一个介于 0 和 100 之间的值，包括 0 和 100。如果这个参数被省略，则被视为 0
     */
    fun toFixed(digits: JsNumber = definedExternally): JsString
    fun toFixed(digits: Int): JsString

    fun toLocaleString(locales: JsString = definedExternally): JsString
    fun toLocaleString(locales: String): JsString

    fun toPrecision(precision: JsNumber = definedExternally): JsString
    fun toPrecision(precision: Int): JsString

    @JsName("toString") fun toJsString(): JsString
    fun toString(radix: JsNumber = definedExternally): JsString
    fun toString(radix: Int): JsString

    fun valueOf(): JsNumber

    companion object {
        val MAX_VALUE: JsNumberObject
        val MIN_VALUE: JsNumberObject
        val NaN: JsNumberObject
        val NEGATIVE_INFINITY: JsNumberObject
        val POSITIVE_INFINITY: JsNumberObject
        val EPSILON: JsNumberObject
        val MIN_SAFE_INTEGER: JsNumberObject
        val MAX_SAFE_INTEGER: JsNumberObject

        fun isFinite(value: JsAny): JsBoolean
        fun isInteger(value: JsAny): JsBoolean
        fun isNaN(value: JsAny): JsBoolean
        fun isSafeInteger(value: JsAny): JsBoolean
        fun parseFloat(value: JsAny): JsNumberObject
        fun parseInt(value: JsAny): JsNumberObject
    }
}

fun JsNumberObject(value: JsAny): JsNumberObject =
    js("new Number(value)")

fun JsNumberObject(value: Byte): JsNumberObject =
    js("new Number(value)")

fun JsNumberObject(value: Short): JsNumberObject =
    js("new Number(value)")

fun JsNumberObject(value: Int): JsNumberObject =
    js("new Number(value)")

fun JsNumberObject(value: Long): JsNumberObject =
    js("new Number(value)")

fun JsNumberObject(value: String): JsNumberObject =
    js("new Number(value)")
