package space.platform.hackathon.data.notifications

import space.platform.hackathon.data.HackathonApi
import space.platform.hackathon.data.base.BaseRemoteDataSource
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.notifications.model.Notification
import space.platform.hackathon.util.response.ApiResponse

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