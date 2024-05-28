@file:Suppress("UNUSED")
package hamusutax.android.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import hamusutax.android.intent.buildIntent

fun Context.startActivity(uri: Uri, action: String = Intent.ACTION_VIEW) =
    startActivity(Intent(action, uri))

fun Context.startActivity(action: String) =
    startActivity(Intent(action))

fun Context.startActivity(uri: Uri, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK) =
    startActivity(
        buildIntent(Intent.ACTION_VIEW, uri) {
            this@buildIntent.flags = flags
        }
    )

fun Context.startActivity(clazz: Class<*>, uri: Uri? = null, flags: Int = Intent.FLAG_ACTIVITY_NEW_TASK) =
    startActivity(
        buildIntent(clazz) {
            data = uri
            this@buildIntent.flags = flags
        }
    )
