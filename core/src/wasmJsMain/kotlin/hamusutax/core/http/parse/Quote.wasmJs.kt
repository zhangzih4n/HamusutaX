package hamusutax.core.http.parse

import hamusutax.wasmjs.external.decodeURIComponent
import hamusutax.wasmjs.external.encodeURIComponent

actual fun String.quote(): String =
    encodeURIComponent(this)

actual fun String.quotePlus(): String =
    encodeURIComponent(this).replace("%20", "+")

actual fun String.unquote(): String =
    decodeURIComponent(this)

actual fun String.unquotePlus(): String =
    decodeURIComponent(replace("+", "%20"))
