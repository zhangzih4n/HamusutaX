@file:Suppress("unused")
package hamusutax.js

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/JSON
 */
@JsName("JSON")
external class JsJson {
    companion object {
        fun isRawJSON(value: dynamic): Boolean
        fun parse(text: String, reviver: dynamic = definedExternally): dynamic
        fun rawJSON(string: String): dynamic

        /**
         * 将 JavaScript 值转换为 JSON 字符串，如果指定了替换函数，则可以选择替换值；
         * 如果指定了替换数组，则可以选择仅包含指定的属性。
         */
        fun stringify(value: dynamic, replacer: dynamic = definedExternally, space: dynamic = definedExternally): String
    }
}

fun Any.isJsRawJson() = JsJson.isRawJSON(this)

fun String.toJsJson() = JsJson.parse(this)

fun String.toJsRawJson() = JsJson.rawJSON(this)
