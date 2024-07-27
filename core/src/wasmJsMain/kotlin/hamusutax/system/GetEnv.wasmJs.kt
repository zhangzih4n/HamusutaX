package hamusutax.system

import hamusutax.wasmjs.jsEval

internal fun getNodeEnv(name: String): String =
    js("process.env[name]")

actual fun getEnvOrNull(name: String): String? =
    if (
        jsEval("\"process\" in window").unsafeCast<JsBoolean>().toBoolean()
        && jsEval("\"env\" in process").unsafeCast<JsBoolean>().toBoolean()
    ) getNodeEnv(name) else null

actual fun getEnv(name: String): String =
    getEnvOrNull(name)!!
