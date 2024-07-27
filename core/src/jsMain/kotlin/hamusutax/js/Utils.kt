@file:Suppress("unused")
package hamusutax.js

@JsName("eval")
external fun jsEval(url: String): dynamic
fun String.evalAsJs() = jsEval(this)

external fun isFinite(testValue: dynamic): Boolean

external fun isNaN(value: dynamic): Boolean

fun jsTypeOf(operand: Any) = js("typeof operand")
