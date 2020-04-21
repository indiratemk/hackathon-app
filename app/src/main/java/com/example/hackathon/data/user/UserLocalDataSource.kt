package com.example.hackathon.data.user

import com.example.hackathon.HackathonApp
import com.example.hackathon.util.PreferenceUtils

class UserLocalDataSource(private val application: HackathonApp) {

    fun getAuthorizedUserId() = PreferenceUtils.getUserId(application)
}