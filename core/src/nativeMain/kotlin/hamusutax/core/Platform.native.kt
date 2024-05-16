package hamusutax.core

import kotlin.experimental.ExperimentalNativeApi

class NativePlatform: Platform {
    @OptIn(ExperimentalNativeApi::class)
    override val name: String = "Native ${kotlin.native.Platform.osFamily} ${kotlin.native.Platform.cpuArchitecture}"
}

actual fun getPlatform(): Platform = NativePlatform()
