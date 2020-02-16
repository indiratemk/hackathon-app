package com.example.hackathon.di

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.util.Constants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {

    single { provideInterceptor() }
    single { provideOkHttpClient(get()) }
    single { provideRetrofit(get()) }
    single { provideHackathonApi(get()) }
    single { AuthRemoteDataSource(get()) }
}

fun provideInterceptor(): Interceptor {
    return Interceptor {chain ->
        val url = chain.request()
                                .url()
                                .newBuilder()
                                .build()

        val request = chain.request()
                                    .newBuilder()
                                    .url(url)
                                    .build()

        chain.proceed(request)
    }
}

fun provideOkHttpClient(interceptor: Interceptor): OkHttpClient {
    return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
}

fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
    return Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(Constants.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create()).build()
}

fun provideHackathonApi(retrofit: Retrofit): HackathonApi {
    return retrofit.create(HackathonApi::class.java)
}