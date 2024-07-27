@file:Suppress("unused")
package hamusutax.datetime

import kotlinx.datetime.Clock.System.now
import kotlinx.datetime.TimeZone
import kotlinx.datetime.offsetAt

fun TimeZone.offsetAtNow() = offsetAt(now())
