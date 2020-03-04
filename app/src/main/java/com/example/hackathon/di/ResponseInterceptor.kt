package com.example.hackathon.di

import android.app.Application
import android.util.Log
import com.example.hackathon.HackathonApp
import com.example.hackathon.util.PreferenceUtils
import okhttp3.Interceptor
import okhttp3.Response
import java.net.HttpCookie

class ResponseInterceptor(private val application: Application) : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val response = chain.proceed(chain.request())
        if (response.code() == 401) {
            PreferenceUtils.setAuthorized(application, false)
            PreferenceUtils.setCookie(application, null)
            (application as HackathonApp).restartApp()
        } else {
            if (response.headers("Set-Cookie").isNotEmpty()) {
                val header = response.header("Set-Cookie")
                if (header != null) {
                    PreferenceUtils.setCookie(application, header)
                }
            }
        }
        return response
    }
}