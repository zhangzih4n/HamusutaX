package hamusutax.core.http.parse

import hamusutax.js.external.decodeURIComponent
import hamusutax.js.external.encodeURIComponent

actual fun String.quote(): String =
    encodeURIComponent(this)

actual fun String.quotePlus(): String =
    encodeURIComponent(this).replace("%20", "+")

actual fun String.unquote(): String =
    decodeURIComponent(this)

actual fun String.unquotePlus(): String =
    decodeURIComponent(this.replace("+", "%20"))
