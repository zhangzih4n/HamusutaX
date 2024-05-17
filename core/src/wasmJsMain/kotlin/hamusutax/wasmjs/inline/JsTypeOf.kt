@file:Suppress("UNUSED")
package hamusutax.wasmjs.inline

fun jsTypeOf(operand: JsAny): String = js("typeof operand")
