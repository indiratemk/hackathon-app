package com.example.hackathon

import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import com.example.hackathon.di.localStorageModule
import com.example.hackathon.di.networkModule
import com.example.hackathon.di.repositoryModule
import com.example.hackathon.di.viewModelModule
import com.example.hackathon.presentation.main.MainActivity
import com.example.hackathon.util.PreferenceUtils
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import kotlin.system.exitProcess

class HackathonApp : Application() {

    override fun onCreate() {
        super.onCreate()

        Stetho.initializeWithDefaults(this)

        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule,
                localStorageModule, repositoryModule, viewModelModule))
        }
    }

    fun restartApp() {
        PreferenceUtils.setAuthorized(this, false)
        PreferenceUtils.clearCookie(this)
        PreferenceUtils.clearUserId(this)
        val intent = Intent(applicationContext, MainActivity::class.java)
        val mPendingIntentId = 123456
        val mPendingIntent = PendingIntent.getActivity(getApplicationContext(), mPendingIntentId, intent, PendingIntent.FLAG_CANCEL_CURRENT)
        val mgr: AlarmManager = applicationContext.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        mgr.set(AlarmManager.RTC, System.currentTimeMillis() + 100, mPendingIntent)
        exitProcess(0)
    }
}