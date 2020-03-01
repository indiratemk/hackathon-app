package com.example.hackathon.di

import android.app.Application
import com.example.hackathon.util.PreferenceUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpCookie

class ResponseInterceptor(private val application: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.headers("Set-Cookie").isNotEmpty()) {
            val header = response.header("Set-Cookie")
            if (header != null) {
                PreferenceUtils.setCookie(application, header)
            }
        }
        return response
    }
}