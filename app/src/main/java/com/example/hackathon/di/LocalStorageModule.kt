package com.example.hackathon.di

import com.example.hackathon.HackathonApp
import com.example.hackathon.data.user.UserLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localStorageModule = module {
    single { UserLocalDataSource(androidApplication() as HackathonApp) }
}