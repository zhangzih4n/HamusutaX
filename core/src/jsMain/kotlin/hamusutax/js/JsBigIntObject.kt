@file:Suppress("unused", "FunctionName")
package hamusutax.js

import kotlin.js.collections.JsArray

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/BigInt
 */
@JsName("BigInt")
external class JsBigIntObject {
    fun toLocaleString(locales: String = definedExternally, options: dynamic = definedExternally): String
    fun toLocaleString(locales: JsArray<String>, options: dynamic = definedExternally): String

    override fun toString(): String

    @JsName("toString") fun toJsString(): String
    @JsName("toString") fun toJsString(radix: Int): String
    fun valueOf(): dynamic

    companion object {
        fun asIntN(width: Int = definedExternally, bigint: JsBigIntObject = definedExternally): JsBigIntObject
        fun asUintN(width: Int = definedExternally, bigint: JsBigIntObject = definedExternally): JsBigIntObject
    }
}

fun BigInt(value: dynamic) = js("Object(BigInt(value))") as JsBigIntObject
