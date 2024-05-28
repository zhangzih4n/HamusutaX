@file:Suppress("UNUSED")
package hamusutax.android.context.notification

import android.app.Notification
import android.app.NotificationManager
import android.content.Context
import android.os.Build.VERSION_CODES
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import java.util.UUID
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun Context.buildNotification(channelId: String, builderAction: NotificationCompat.Builder.() -> Unit): Notification {
    contract { callsInPlace(builderAction, InvocationKind.EXACTLY_ONCE) }
    return NotificationCompat.Builder(this, channelId).apply(builderAction).build()
}

/**
 * 删除所有通知渠道
 */
@RequiresApi(VERSION_CODES.O)
fun NotificationManager.deleteAllNotificationChannel() {
    notificationChannels.forEach {
        deleteNotificationChannel(it.id)
    }
}

/**
 * 删除所有通知渠道组及从属的通知渠道
 */
@RequiresApi(VERSION_CODES.O)
fun NotificationManager.deleteAllNotificationChannelGroup() {
    notificationChannelGroups.forEach {
        deleteNotificationChannelGroup(it.id)
    }
}

/**
 * 发送通知并返回使用的随机 ID
 */
fun NotificationManager.notify(notification: Notification) =
    UUID.randomUUID().hashCode().also {
        notify(UUID.randomUUID().hashCode(), notification)
    }


fun Context.showToast(text: CharSequence, duration: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, text, duration).show()
}
