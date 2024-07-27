@file:Suppress("unused")
package hamusutax.wasmjs

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/JSON
 */
@JsName("JSON")
external class JsJson : JsAny {
    companion object {
        fun isRawJSON(value: JsAny): JsBoolean

        fun parse(text: JsString, reviver: JsAny = definedExternally): JsJson
        fun parse(text: String, reviver: JsAny = definedExternally): JsJson

        fun rawJSON(string: JsString): JsAny
        fun rawJSON(string: String): JsAny

        /**
         * 将 JavaScript 值转换为 JSON 字符串，如果指定了替换函数，则可以选择替换值；
         * 如果指定了替换数组，则可以选择仅包含指定的属性。
         */
        fun stringify(value: JsAny, replacer: JsAny = definedExternally, space: JsAny = definedExternally): JsString
    }
}

fun JsAny.isJsRawJson() = JsJson.isRawJSON(this)

fun JsString.toJsJson() = JsJson.parse(this)
fun String.toJsJson() = JsJson.parse(this)

fun JsString.toJsRawJson() = JsJson.rawJSON(this)
fun String.toJsRawJson() = JsJson.rawJSON(this)

fun JsAny.stringify() = JsJson.stringify(this)
fun JsAny.stringify(replacer: JsAny) = JsJson.stringify(this, replacer)
fun JsAny.stringify(replacer: JsAny, space: JsAny) = JsJson.stringify(this, replacer, space)
