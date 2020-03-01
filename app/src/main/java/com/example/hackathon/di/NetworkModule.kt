package com.example.hackathon.di

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.user.UserRemoteDataStore
import com.example.hackathon.util.Constants
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
    single { UserRemoteDataStore(get()) }
}

fun provideOkHttpClient(responseInterceptor: ResponseInterceptor,
                        requestInterceptor: RequestInterceptor): OkHttpClient {
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