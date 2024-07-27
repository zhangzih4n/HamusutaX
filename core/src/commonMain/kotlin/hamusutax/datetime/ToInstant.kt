@file:Suppress("unused")
package hamusutax.datetime

import kotlinx.datetime.Instant

fun Long.epochSecondsToInstant() =
    Instant.fromEpochSeconds(this)

fun Long.epochMillisecondsToInstant() =
    Instant.fromEpochMilliseconds(this)
