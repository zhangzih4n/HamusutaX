package hamusutax.core

interface Platform {
    val name: String
}

expect fun getPlatform(): hamusutax.core.Platform
