package com.example.hackathon.presentation.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.domain.notifications.NotificationsRepository
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class NotificationsViewModel(private val notificationsRepository: NotificationsRepository) :
    BaseViewModel() {

    private val _notifications = MutableLiveData<State<Result<List<Notification>, Unit>>>()

    val notifications: LiveData<State<Result<List<Notification>, Unit>>>
        get() = _notifications

    init {
        getNotifications()
    }

    fun getNotifications() {
        coroutineContext.launch {
            _notifications.value = notificationsRepository.getNotifications()
        }
    }
}