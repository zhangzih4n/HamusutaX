@file:Suppress("unused")
package hamusutax.wasmjs

external fun decodeURI(encodedURI: JsString): JsString
external fun decodeURI(encodedURI: String): JsString

external fun decodeURIComponent(encodedURI: JsString): JsString
external fun decodeURIComponent(encodedURI: String): JsString

external fun encodeURI(uri: JsString): JsString
external fun encodeURI(uri: String): JsString

external fun encodeURIComponent(uriComponent: JsString): JsString
external fun encodeURIComponent(uriComponent: String): JsString
