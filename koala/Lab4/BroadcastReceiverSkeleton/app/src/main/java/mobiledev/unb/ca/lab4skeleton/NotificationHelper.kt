package mobiledev.unb.ca.lab4skeleton

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class NotificationHelper {
    fun handleNotification(context: Context) {
        // TODO: Create the notification channel

        // TODO: Create the intent

        // TODO: Create the Notification

        // Show the notification
        // showNotification(context, builder)
    }

//    private fun showNotification(context: Context, builder: NotificationCompat.Builder) {
//        with(NotificationManagerCompat.from(context)) {
//            if (ContextCompat.checkSelfPermission(
//                    context,
//                    Manifest.permission.POST_NOTIFICATIONS
//                ) != PackageManager.PERMISSION_GRANTED
//            ) {
//                return
//            }
//            notify(Constants.NOTIFICATION_REQUEST_ID, builder.build())
//        }
//    }
}