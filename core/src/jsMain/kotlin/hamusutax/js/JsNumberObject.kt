@file:Suppress("unused")
package hamusutax.js

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/Number
 */
@JsName("Number")
external class JsNumberObject(value: dynamic) {
    fun toExponential(fractionDigits: Int = definedExternally): String
    fun toFixed(digits: Int = definedExternally): String
    fun toLocaleString(locales: String = definedExternally, options: dynamic = definedExternally): String
    fun toLocaleString(locales: Array<String>, options: dynamic = definedExternally): String
    fun toPrecision(precision: Int = definedExternally): String
    override fun toString(): String
    fun toString(radix: Int = definedExternally): String
    fun valueOf(): Double

    companion object {
        val MAX_VALUE: JsNumberObject
        val MIN_VALUE: JsNumberObject
        val NaN: JsNumberObject
        val NEGATIVE_INFINITY: JsNumberObject
        val POSITIVE_INFINITY: JsNumberObject
        val EPSILON: JsNumberObject
        val MIN_SAFE_INTEGER: JsNumberObject
        val MAX_SAFE_INTEGER: JsNumberObject

        fun isFinite(value: dynamic): Boolean
        fun isInteger(value: dynamic): Boolean
        fun isNaN(value: dynamic): Boolean
        fun isSafeInteger(value: dynamic): Boolean
        fun parseFloat(value: dynamic): JsNumberObject
        fun parseInt(value: dynamic): JsNumberObject
    }
}
