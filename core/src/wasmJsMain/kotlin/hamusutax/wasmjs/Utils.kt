@file:Suppress("unused")
package hamusutax.wasmjs

@JsName("eval")
external fun jsEval(script: String): JsAny
fun String.evalAsJs() = jsEval(this)

external fun isFinite(testValue: JsAny): JsBoolean
fun JsAny.isFinite() = isFinite(this)

external fun isNaN(value: JsAny): JsBoolean
fun JsAny.isNaN() = isNaN(this)

fun jsTypeOf(operand: JsAny): String = js("typeof operand")
val JsAny.type get() = jsTypeOf(this)
