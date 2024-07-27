package hamusutax.system

import kotlinx.cinterop.toKString
import platform.posix.getenv

actual fun getEnvOrNull(name: String) =
    getenv(name)?.toKString()

actual fun getEnv(name: String) =
    getenv(name)!!.toKString()
