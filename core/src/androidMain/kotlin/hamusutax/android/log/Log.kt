@file:Suppress("unused")
package hamusutax.android.log

import android.util.Log

fun Throwable.reportLog(message: String? = this.message, tag: String = this::class.simpleName ?: "<UnknownException>") {
    Log.e(tag, message.toString())
}
