package az.altacademy.androidgroup2.lessons.firestore

import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import az.altacademy.androidgroup2.R
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class FirebasePushService: FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {

        val notification = NotificationCompat.Builder(this, "group2")
            .setContentTitle(message.notification?.title)
            .setContentText(message.notification?.body)
            .setSmallIcon(R.drawable.ic_play)
            .build()
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                "group2",
                "push",
                NotificationManager.IMPORTANCE_LOW
            )
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(111, notification)
//        super.onMessageReceived(message)
    }

    override fun onNewToken(token: String) {
        Log.d("FirebaseMessagingTag", "token: $token")
        super.onNewToken(token)
    }
}