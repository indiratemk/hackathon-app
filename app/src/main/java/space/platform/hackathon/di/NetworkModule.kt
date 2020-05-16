package space.platform.hackathon.di

import space.platform.hackathon.data.HackathonApi
import space.platform.hackathon.data.auth.AuthRemoteDataSource
import space.platform.hackathon.data.hackathon.HackathonRemoteDataSource
import space.platform.hackathon.data.notifications.NotificationsRemoteDataSource
import space.platform.hackathon.data.participants.ParticipantsRemoteDataSource
import space.platform.hackathon.data.teams.TeamRemoteDataSource
import space.platform.hackathon.data.user.UserRemoteDataSource
import space.platform.hackathon.di.interceptors.RequestInterceptor
import space.platform.hackathon.di.interceptors.ResponseInterceptor
import space.platform.hackathon.util.Constants
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single { provideOkHttpClient(ResponseInterceptor(androidApplication()), RequestInterceptor(androidApplication())) }
    single { provideRetrofit(get()) }
    single { provideHackathonApi(get()) }
    single { AuthRemoteDataSource(get()) }
    single { UserRemoteDataSource(get()) }
    single { HackathonRemoteDataSource(get()) }
    single { ParticipantsRemoteDataSource(get()) }
    single { TeamRemoteDataSource(get()) }
    single { NotificationsRemoteDataSource(get()) }
}

fun provideOkHttpClient(responseInterceptor: ResponseInterceptor,
                        requestInterceptor: RequestInterceptor
): OkHttpClient {
    return OkHttpClient().newBuilder()
        .addInterceptor(requestInterceptor)
        .addInterceptor(responseInterceptor)
        .build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
}

fun provideHackathonApi(retrofit: Retrofit): HackathonApi {
    return retrofit.create(HackathonApi::class.java)
}