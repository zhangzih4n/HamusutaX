@file:Suppress("unused")
package hamusutax.ktor.utils.date

import io.ktor.util.date.GMTDate
import kotlinx.datetime.Instant

inline fun GMTDate.toInstant() =
    Instant.fromEpochMilliseconds(timestamp)
