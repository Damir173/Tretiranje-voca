package hr.ferit.tretiranjevoca

import android.app.Application

class TretiranjeVoca: Application() {

    override fun onCreate() {
        super.onCreate()
        application = this
    }

    companion object{
        lateinit var application: Application
    }
}