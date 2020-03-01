package com.example.hackathon.di

import android.app.Application
import com.example.hackathon.util.PreferenceUtils
import okhttp3.Interceptor
import okhttp3.Response

class RequestInterceptor(private val application: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val builder = chain.request().newBuilder()
        if (PreferenceUtils.getCookie(application) != null) {
            //todo fix
            builder.addHeader("Cookie", PreferenceUtils.getCookie(application))
        }
        return chain.proceed(builder.build())
    }
}