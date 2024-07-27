@file:Suppress("unused")
package hamusutax.ktor.utils

import io.ktor.util.StringValues
import io.ktor.util.StringValuesBuilder
import io.ktor.util.StringValuesBuilderImpl
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

inline fun buildStringValues(
    caseInsensitiveName: Boolean = false,
    size: Int = 8,
    builderAction: StringValuesBuilder.() -> Unit
): StringValues {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return StringValuesBuilderImpl(caseInsensitiveName, size).apply(builderAction).build()
}
