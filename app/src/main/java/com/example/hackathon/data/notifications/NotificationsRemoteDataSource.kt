package com.example.hackathon.data.notifications

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.util.response.ApiResponse

class NotificationsRemoteDataSource(private val hackathonApi: HackathonApi) :
    BaseRemoteDataSource() {

    suspend fun getNotificationsCount(): ApiResponse<Result<HashMap<String, Int>, Unit>> {
        return getResponse { hackathonApi.getNotificationsCount() }
    }

    suspend fun getNotifications(): ApiResponse<Result<List<Notification>, Unit>> {
        return getResponse { hackathonApi.getNotifications() }
    }

    suspend fun removeNotification(id: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.removeNotification(id) }
    }

    suspend fun sendFeedback(hackathonId: Int, message: String, score: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.sendFeedback(hackathonId, message, score) }
    }
}