package com.example.hackathon.util

import android.content.Context

object PreferenceUtils {
    private const val PREF_IS_AUTHORIZED = "pref_authorized"
    private const val PREF_COOKIE = "pref_cookie"
    private const val PREF_USER_ID = "pref_user_id"

    private fun getPreferences(context: Context)
            = context.getSharedPreferences("hackathon_app",Context.MODE_PRIVATE)

    fun isAuthorized(context: Context)
            = getPreferences(context).getBoolean(PREF_IS_AUTHORIZED, false)

    //TODO rewrite logic for saving shared prefs

    fun setAuthorized(context: Context, isAuthorized: Boolean) {
        getPreferences(context).edit().putBoolean(PREF_IS_AUTHORIZED, isAuthorized).commit()
    }

    fun getCookie(context: Context)
            = getPreferences(context).getString(PREF_COOKIE, null)

    fun setCookie(context: Context, cookie: String?) {
        getPreferences(context).edit().putString(PREF_COOKIE, cookie).commit()
    }

    fun clearCookie(context: Context) {
        getPreferences(context).edit().remove(PREF_COOKIE).commit()
    }

    fun setUserId(context: Context, userId: Int) {
        getPreferences(context).edit().putInt(PREF_USER_ID, userId).apply()
    }

    fun getUserId(context: Context) = getPreferences(context).getInt(PREF_USER_ID, -1)

    fun clearUserId(context: Context) {
        getPreferences(context).edit().remove(PREF_USER_ID).apply()
    }
}