@file:Suppress("UNUSED")
package hamusutax.core.datetime

actual fun currentTimeMilliseconds() =
    System.currentTimeMillis()

actual fun currentTimeSeconds() =
    System.currentTimeMillis() / 1000
