@file:Suppress("UNUSED")
package hamusutax.core.datetime

import kotlinx.datetime.Clock

actual fun getTimeMillis() =
    Clock.System.now().toEpochMilliseconds()

actual fun getTimeSeconds() =
    Clock.System.now().epochSeconds
