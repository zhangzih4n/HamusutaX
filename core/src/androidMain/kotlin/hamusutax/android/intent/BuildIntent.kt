@file:Suppress("unused")
package hamusutax.android.intent

import android.content.Context
import android.content.Intent
import android.net.Uri
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun buildIntent(action: String, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(action).apply(builderAction)
}

inline fun buildIntent(action: String, uri: Uri, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(action, uri).apply(builderAction)
}

inline fun Context.buildIntent(clazz: Class<*>, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(this, clazz).apply(builderAction)
}

inline fun Context.buildIntent(action: String, uri: Uri, clazz: Class<*>, builderAction: Intent.() -> Unit): Intent {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return Intent(action, uri, this, clazz).apply(builderAction)
}
