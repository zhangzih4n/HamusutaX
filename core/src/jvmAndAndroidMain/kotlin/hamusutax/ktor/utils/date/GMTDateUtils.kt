@file:Suppress("UNUSED")
package hamusutax.ktor.utils.date

import io.ktor.util.date.GMTDate
import kotlinx.datetime.Instant

fun GMTDate.toInstant() =
    Instant.fromEpochMilliseconds(GMTDate().timestamp)
