package space.platform.hackathon.di

import space.platform.hackathon.HackathonApp
import space.platform.hackathon.data.user.UserLocalDataSource
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val localStorageModule = module {
    single { UserLocalDataSource(androidApplication() as HackathonApp) }
}