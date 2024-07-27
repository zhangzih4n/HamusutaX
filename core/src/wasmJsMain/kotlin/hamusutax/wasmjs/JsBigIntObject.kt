@file:Suppress("unused")
package hamusutax.wasmjs

/**
 * https://developer.mozilla.org/zh-CN/docs/Web/JavaScript/Reference/Global_Objects/BigInt
 */
@JsName("BigInt")
external class JsBigIntObject : JsAny {
    fun toLocaleString(locales: JsString = definedExternally, options: JsAny = definedExternally): JsString
    fun toLocaleString(locales: String, options: JsAny = definedExternally): JsString
    fun toLocaleString(locales: JsArray<JsString>, options: JsAny = definedExternally): JsString

    @JsName("toString") fun toJsString(radix: Int = definedExternally): JsString

    fun valueOf(): JsBigInt

    companion object {
        fun asIntN(width: JsNumber, bigint: JsBigInt): JsBigIntObject
        fun asIntN(width: JsNumber, bigint: JsBigIntObject): JsBigIntObject
        fun asIntN(width: Int, bigint: JsBigInt): JsBigIntObject
        fun asIntN(width: Int, bigint: JsBigIntObject): JsBigIntObject

        fun asUintN(width: JsNumber, bigint: JsBigInt): JsBigIntObject
        fun asUintN(width: JsNumber, bigint: JsBigIntObject): JsBigIntObject
        fun asUintN(width: Int, bigint: JsBigInt): JsBigIntObject
        fun asUintN(width: Int, bigint: JsBigIntObject): JsBigIntObject
    }
}

fun JsBigIntObject(value: JsAny): JsBigIntObject =
    js("Object(BigInt(value))")

fun JsBigIntObject(value: Byte): JsBigIntObject =
    js("Object(BigInt(value))")

fun JsBigIntObject(value: Short): JsBigIntObject =
    js("Object(BigInt(value))")

fun JsBigIntObject(value: Int): JsBigIntObject =
    js("Object(BigInt(value))")

fun JsBigIntObject(value: Long): JsBigIntObject =
    js("Object(BigInt(value))")

fun JsBigIntObject(value: String): JsBigIntObject =
    js("Object(BigInt(value))")
