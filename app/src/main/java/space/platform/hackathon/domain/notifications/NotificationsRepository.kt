package space.platform.hackathon.domain.notifications

import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.notifications.NotificationsRemoteDataSource
import space.platform.hackathon.data.notifications.model.Notification
import space.platform.hackathon.domain.base.BaseRepository
import space.platform.hackathon.util.state.State

class NotificationsRepository(private val notificationsRemoteDataSource: NotificationsRemoteDataSource) :
    BaseRepository(), INotificationsRepository {

    override suspend fun getNotificationsCount(): State<Result<HashMap<String, Int>, Unit>> {
        return handleState { notificationsRemoteDataSource.getNotificationsCount() }
    }

    override suspend fun getNotifications(): State<Result<List<Notification>, Unit>> {
        return handleState { notificationsRemoteDataSource.getNotifications() }
    }

    override suspend fun removeNotification(id: Int): State<Result<Boolean, Unit>> {
        return handleState { notificationsRemoteDataSource.removeNotification(id) }
    }

    override suspend fun sendFeedback(hackathonId: Int, message: String, score: Int): State<Result<Boolean, Unit>> {
        return handleState { notificationsRemoteDataSource.sendFeedback(hackathonId, message, score) }
    }
}