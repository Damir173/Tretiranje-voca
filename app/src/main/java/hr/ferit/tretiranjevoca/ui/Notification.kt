package hr.ferit.tretiranjevoca.ui

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import hr.ferit.tretiranjevoca.R


const val notificationID = 1
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

@Suppress("DEPRECATION")
class Notification : BroadcastReceiver()
{
    override fun onReceive(context: Context, intent: Intent)
    {


        var notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.fruitsicon)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentText(intent.getStringExtra(messageExtra))
            .setStyle(NotificationCompat.BigTextStyle()
                .bigText(intent.getStringExtra(messageExtra)))
            .build()


        val  manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID, notification)
    }

}