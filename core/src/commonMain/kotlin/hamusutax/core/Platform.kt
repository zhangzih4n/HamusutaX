package hamusutax.core

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform
