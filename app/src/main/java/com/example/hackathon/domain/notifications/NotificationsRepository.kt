package com.example.hackathon.domain.notifications

import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.notifications.NotificationsRemoteDataSource
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.util.state.State

class NotificationsRepository(private val notificationsRemoteDataSource: NotificationsRemoteDataSource) :
    BaseRepository(), INotificationsRepository {

    override suspend fun getNotificationsCount(): State<Result<HashMap<String, Int>, Unit>> {
        return handleState { notificationsRemoteDataSource.getNotificationsCount() }
    }

    override suspend fun getNotifications(): State<Result<List<Notification>, Unit>> {
        return handleState { notificationsRemoteDataSource.getNotifications() }
    }
}