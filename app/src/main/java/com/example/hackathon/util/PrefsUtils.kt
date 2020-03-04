package com.example.hackathon.util

import android.content.Context
import android.preference.PreferenceManager

object PreferenceUtils {
    private const val PREF_IS_AUTHORIZED = "pref_authorized"
    private const val PREF_COOKIE = "pref_cookie"

    private fun getPreferences(context: Context)
            = PreferenceManager.getDefaultSharedPreferences(context)

    fun isAuthorized(context: Context)
            = getPreferences(context).getBoolean(PREF_IS_AUTHORIZED, false)

    fun setAuthorized(context: Context, isAuthorized: Boolean) {
        getPreferences(context).edit().putBoolean(PREF_IS_AUTHORIZED, isAuthorized).apply()
    }

    fun getCookie(context: Context)
            = getPreferences(context).getString(PREF_COOKIE, null)

    fun setCookie(context: Context, cookie: String?) {
        getPreferences(context).edit().putString(PREF_COOKIE, cookie).apply()
    }
}