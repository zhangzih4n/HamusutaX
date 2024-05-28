@file:Suppress("UNUSED")
package hamusutax.core.datetime

import kotlinx.datetime.Clock

actual fun currentTimeMilliseconds() =
    Clock.System.now().toEpochMilliseconds()

actual fun currentTimeSeconds() =
    Clock.System.now().epochSeconds
