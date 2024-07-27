package hamusutax.android.content

import android.content.ContentResolver
import android.net.Uri
import kotlinx.io.asSink
import kotlinx.io.asSource
import kotlinx.io.buffered

fun ContentResolver.openSink(uri: Uri) =
    openOutputStream(uri)?.asSink()?.buffered()

fun ContentResolver.openSource(uri: Uri) =
    openInputStream(uri)?.asSource()?.buffered()
