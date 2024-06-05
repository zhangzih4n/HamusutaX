@file:Suppress("UNUSED")
package hamusutax.core.datetime

actual fun getTimeMillis() =
    System.currentTimeMillis()

actual fun getTimeSeconds() =
    System.currentTimeMillis() / 1000
