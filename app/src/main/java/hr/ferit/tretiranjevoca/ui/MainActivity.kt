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
        val message = "Ukupno tretiranja: " + tretiranjeRepository.getNumber().toString() +
                     "\n[SLJIVE]: " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString() +
                     "\n[VINOVA LOZA]: " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString() +
                     "\n[JABUKE]: " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString() +
                     "\n[KRUSKE]: " + tretiranjeRepository.getAktivneSljive(System.currentTimeMillis()).toString()
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
            set(Calendar.HOUR_OF_DAY, 7)
            set(Calendar.MINUTE, 43)
        }

        alarmManager.setRepeating(
            AlarmManager.RTC_WAKEUP,
            calendar.timeInMillis,
            1000 * 60 * 1,
            pendingIntent
        )
    }

    private fun showAlert(time: Long, title: String, message: String)
    {
        val date = Date(time)
        val dateFormat = android.text.format.DateFormat.getLongDateFormat(applicationContext)
        val timeFormat = android.text.format.DateFormat.getTimeFormat(applicationContext)

        AlertDialog.Builder(this)
            .setTitle("Notification Scheduled")
            .setMessage(
                "Title: " + title +
                        "\nMessage: " + message +
                        "\nAt: " + dateFormat.format(date) + " " + timeFormat.format(date))
            .setPositiveButton("Okay"){_,_ ->}
            .show()
    }



    private fun createNotificationChannel()
    {
        val name = "Obavijesti o tretiranjima"
        val desc = "Ovdje se nalaze obavijesti o tretiranjima"
        val importance = NotificationManager.IMPORTANCE_DEFAULT
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)

    }






}

