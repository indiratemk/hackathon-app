package com.example.hackathon.domain.notifications

import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.util.state.State

interface INotificationsRepository {

    suspend fun getNotificationsCount(): State<Result<HashMap<String, Int>, Unit>>

    suspend fun getNotifications(): State<Result<List<Notification>, Unit>>

    suspend fun removeNotification(id: Int): State<Result<Boolean, Unit>>
}