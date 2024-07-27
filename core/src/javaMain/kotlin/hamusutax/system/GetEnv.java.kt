package hamusutax.system

actual fun getEnvOrNull(name: String) =
    System.getenv(name) ?: null

actual fun getEnv(name: String) =
    System.getenv(name)!!
