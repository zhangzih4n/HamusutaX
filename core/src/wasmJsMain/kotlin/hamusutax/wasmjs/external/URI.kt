@file:Suppress("UNUSED")
package hamusutax.wasmjs.external

external fun eval(url: String): JsAny

external fun isFinite(testValue: JsAny): Boolean

external fun isNaN(value: JsAny): Boolean
