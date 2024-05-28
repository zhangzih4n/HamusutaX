package hamusutax.ktor.utils

import io.ktor.util.StringValuesBuilder
import io.ktor.util.StringValuesBuilderImpl

fun buildStringValues(
    caseInsensitiveName: Boolean = false,
    builder: StringValuesBuilder.() -> Unit
) =
    StringValuesBuilderImpl(caseInsensitiveName).apply(builder).build()
