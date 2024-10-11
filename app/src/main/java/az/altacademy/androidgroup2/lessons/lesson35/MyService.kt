package az.altacademy.androidgroup2.lessons.lesson35

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import az.altacademy.androidgroup2.R

class MyService: Service() {

    private var notificationManager: NotificationManager? = null

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
        notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        val action = intent?.action

        when(action){
            PlayerActions.START.toString() -> {
//                updateNotification(10)
                startForeground(111, createNotification(0))
            }

            PlayerActions.PLAY.toString() -> {
//                updateNotification(40)
                startForeground(111, createNotification(10))
            }

            PlayerActions.PAUSE.toString() -> {
//                updateNotification(60)
                startForeground(111, createNotification(40))
            }

            PlayerActions.STOP.toString() -> {
//                updateNotification(100)
                startForeground(111, createNotification(100))
//                stopSelf()

            }

            PlayerActions.CANCEL.toString() -> {
                sendMessage(PlayerActions.CANCEL.toString())
                stopSelf()

            }
        }
        return super.onStartCommand(intent, flags, startId)
    }

    private fun sendMessage(message: String){
        sendBroadcast(Intent(message))
    }


    private fun createNotification(progress: Int): Notification {
        val cancelIntent = Intent(this, MyService::class.java).apply {
            action = PlayerActions.CANCEL.toString()
        }
        val cancelPendingIntent = PendingIntent.getService(this, 0, cancelIntent, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)

        return NotificationCompat.Builder(this, "MyService")
            .setContentTitle("Uploading")
            .setContentText("Upload in progress")
            .setSmallIcon(R.drawable.ic_home)
            .setProgress(100, progress, false)
            .addAction(0,"Cancel", cancelPendingIntent)
            .build()
    }


    private fun updateNotification(progress: Int) {
        val notification = createNotification(progress)
        notificationManager?.notify(111, notification)
    }

    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            "MyService",
            "Upload Channel",
            NotificationManager.IMPORTANCE_LOW
        )
        notificationManager?.createNotificationChannel(channel)
    }
}