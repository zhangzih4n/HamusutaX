@file:Suppress("unused")
package hamusutax.android.intent

import android.content.Intent
import hamusutax.serialization.JsonEncodeDefaults
import kotlinx.serialization.encodeToString

inline fun <reified T> Intent.putJsonExtra(name: String, value: T): Intent {
    val serialized = JsonEncodeDefaults.encodeToString(value)
    return putExtra(name, serialized)
}

inline fun <reified T> Intent.getJsonExtra(name: String): T? {
    val value = getStringExtra(name) ?: return null
    return JsonEncodeDefaults.decodeFromString<T>(value)
}
