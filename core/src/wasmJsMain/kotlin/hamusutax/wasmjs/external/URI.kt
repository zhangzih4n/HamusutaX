@file:Suppress("UNUSED")
package hamusutax.wasmjs.external

external fun encodeURI(url: String): String

external fun decodeURI(url: String): String

external fun encodeURIComponent(url: String): String

external fun decodeURIComponent(url: String): String
