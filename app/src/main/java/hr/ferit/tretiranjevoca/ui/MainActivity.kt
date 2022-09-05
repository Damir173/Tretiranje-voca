package hr.ferit.tretiranjevoca.ui


import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hr.ferit.tretiranjevoca.R
import hr.ferit.tretiranjevoca.databinding.ActivityMainBinding
import hr.ferit.tretiranjevoca.di.TretiranjeRepositoryFactory

import java.util.*


class MainActivity : AppCompatActivity() {

   private lateinit var binding: ActivityMainBinding
    private val tretiranjeRepository = TretiranjeRepositoryFactory.tretiranjeRepository


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        createNotificationChannel()
        scheduleNotification()
    }

    private fun scheduleNotification()
    {
        val intent = Intent(applicationContext, Notification::class.java)
        val title =getString(R.string.notificationTitleString)
        val message = getString(R.string.nfcukupno) + tretiranjeRepository.getNumber().toString() +
                     "\nTretiranja šljiva: " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString() +
                     "\nTretiranja jabuka: " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString() +
                     "\nTretiranja vinove loze: " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString() +
                     "\nTretiranja kruške " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString()
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            applicationContext,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val calendar: Calendar = Calendar.getInstance().apply {
            timeInMillis = System.currentTimeMillis()
            set(Calendar.HOUR_OF_DAY, 8)
            set(Calendar.MINUTE, 0)
        }

        // test - 180 min
        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            1000 * 60 * 180,
            pendingIntent
        )
    }





    private fun createNotificationChannel()
    {
        val name = getString(R.string.nfcobavijest1)
        val desc = getString(R.string.nfcdescription)
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }






}

