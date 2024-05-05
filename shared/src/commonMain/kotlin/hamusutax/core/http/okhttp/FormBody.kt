@file:Suppress("UNUSED")
package hamusutax.core.http.okhttp

import okhttp3.FormBody
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind.EXACTLY_ONCE
import kotlin.contracts.contract

private val EmptyFormBody = FormBody.Builder().build()

fun emptyFormBody() = EmptyFormBody

@OptIn(ExperimentalContracts::class)
inline fun buildFormBody(builderAction: FormBody.Builder.() -> Unit): FormBody {
    contract { callsInPlace(builderAction, EXACTLY_ONCE) }
    return FormBody.Builder().apply(builderAction).build()
}
