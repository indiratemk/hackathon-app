package space.platform.hackathon.domain.notifications

import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.notifications.model.Notification
import space.platform.hackathon.util.state.State

interface INotificationsRepository {

    suspend fun getNotificationsCount(): State<Result<HashMap<String, Int>, Unit>>

    suspend fun getNotifications(): State<Result<List<Notification>, Unit>>

    suspend fun removeNotification(id: Int): State<Result<Boolean, Unit>>

    suspend fun sendFeedback(hackathonId: Int, message: String, score: Int): State<Result<Boolean, Unit>>
}