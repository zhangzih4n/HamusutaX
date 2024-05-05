package hamusutax.core

class Greeting {
    private val platform: hamusutax.core.Platform = hamusutax.core.getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}
