package space.platform.hackathon.data.user

import space.platform.hackathon.HackathonApp
import space.platform.hackathon.util.PreferenceUtils

class UserLocalDataSource(private val application: HackathonApp) {

    fun getAuthorizedUserId() = PreferenceUtils.getUserId(application)
}