package hamusutax.system

actual fun getEnvOrNull(name: String) =
    js("process.env[name]") as String?

actual fun getEnv(name: String) =
    js("process.env[name]") as String
