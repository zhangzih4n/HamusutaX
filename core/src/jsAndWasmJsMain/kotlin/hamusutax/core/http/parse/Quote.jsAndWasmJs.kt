@file:Suppress("UNUSED")
package hamusutax.core.http.parse

import hamusutax.jsandwasmjs.external.decodeURIComponent
import hamusutax.jsandwasmjs.external.encodeURIComponent

actual fun String.quote(): String =
    encodeURIComponent(this)

actual fun String.quotePlus(): String =
    encodeURIComponent(this).replace("%20", "+")

actual fun String.unquote(): String =
    decodeURIComponent(this)

actual fun String.unquotePlus(): String =
    decodeURIComponent(this.replace("+", "%20"))
