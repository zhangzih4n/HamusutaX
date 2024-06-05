@file:Suppress("UNUSED")
package hamusutax.core.datetime

import kotlinx.datetime.Instant

val Long.epochSeconds get() =
    Instant.fromEpochSeconds(this)

val Long.epochMilliseconds get() =
    Instant.fromEpochMilliseconds(this)
