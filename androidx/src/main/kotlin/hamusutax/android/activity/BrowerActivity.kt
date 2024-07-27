package hamusutax.android.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.browser.customtabs.CustomTabsIntent

fun Context.startBrowser(uri: Uri, intentAction: (Intent.() -> Unit) = {}) {
    val intent = CustomTabsIntent.Builder().build().intent.apply {
        data = uri
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    intent.apply(intentAction)
    startActivity(intent)
}

fun Context.startBrowser(uriString: String, intentAction: (Intent.() -> Unit) = {}) =
    startBrowser(Uri.parse(uriString), intentAction)
