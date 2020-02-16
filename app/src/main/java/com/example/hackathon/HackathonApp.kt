package com.example.hackathon

import android.app.Application
import com.example.hackathon.di.networkModule
import com.example.hackathon.di.repositoryModule
import com.example.hackathon.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class HackathonApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(listOf(networkModule, repositoryModule, viewModelModule))
        }
    }
}