package hamusutax.core.http.parse

actual fun String.quote() =
    js("encodeURIComponent(`$this`)") as String

actual fun String.quotePlus() =
    js("encodeURIComponent(`$this`).replace('%20', '+')") as String

actual fun String.unquote() =
    js("decodeURIComponent(`$this`)") as String

actual fun String.unquotePlus() =
    js("decodeURIComponent(`$this`.replace('+', '%20'))") as String
