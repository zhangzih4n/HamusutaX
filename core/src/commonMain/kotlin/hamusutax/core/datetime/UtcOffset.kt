@file:Suppress("UNUSED")
package hamusutax.core.datetime

import kotlinx.datetime.Clock
import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt

fun TimeZone.offsetAtNow() = offsetAt(Clock.System.now())
