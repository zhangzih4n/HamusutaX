@file:Suppress("UNUSED")
package hamusutax.android.systemservice.notification

import android.app.Notification
import android.app.NotificationChannel
import android.content.Context
import androidx.core.app.NotificationCompat
import hamusutax.android.systemservice.getNotificationManager
import java.util.UUID
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Context.buildNotification(channelId: String, builderAction: NotificationCompat.Builder.() -> Unit): Notification {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return NotificationCompat.Builder(this, channelId).apply(builderAction).build()
}

fun Context.createNotificationChannel(channel: NotificationChannel) {
    with(getNotificationManager()!!) {
        getNotificationChannel(channel.id)?.let {
            createNotificationChannel(channel)
        }
    }
}

fun Context.deleteNotificationChannel(channel: NotificationChannel) =
    deleteNotificationChannel(channel.id)

fun Context.deleteNotificationChannel(channelId: String) {
    with(getNotificationManager()!!) {
        getNotificationChannel(channelId)?.let {
            deleteNotificationChannel(channelId)
        }
    }
}

fun Context.deleteAllNotificationChannel() {
    with(getNotificationManager()!!) {
        notificationChannels.forEach {
            deleteNotificationChannel(it)
        }
    }
}

fun Context.notify(id: Int = UUID.randomUUID().hashCode(), notification: Notification) {
    with(getNotificationManager()!!) {
        notify(id, notification)
    }
}
