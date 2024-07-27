@file:Suppress("unused")
package hamusutax.android.browser.customtabs

import androidx.browser.customtabs.CustomTabsIntent
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

fun buildCustomTabsIntent(builderAction: (CustomTabsIntent.Builder.() -> Unit)): CustomTabsIntent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return CustomTabsIntent.Builder().apply(builderAction).build()
}
