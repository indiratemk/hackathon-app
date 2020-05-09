package com.example.hackathon.presentation.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.domain.notifications.NotificationsRepository
import com.example.hackathon.domain.team.TeamRepository
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class NotificationsViewModel(private val notificationsRepository: NotificationsRepository,
                             private val teamRepository: TeamRepository) :
    BaseViewModel() {

    private val _notifications = MutableLiveData<State<Result<List<Notification>, Unit>>>()
    private val _isRemoved = MutableLiveData<State<Result<Boolean, Unit>>>()
    private val _isAccepted = MutableLiveData<State<Result<Boolean, Unit>>>()

    val notifications: LiveData<State<Result<List<Notification>, Unit>>>
        get() = _notifications

    val isRemoved: LiveData<State<Result<Boolean, Unit>>>
        get() = _isRemoved

    val isAccepted: LiveData<State<Result<Boolean, Unit>>>
        get() = _isAccepted

    init {
        getNotifications()
    }

    fun getNotifications() {
        coroutineContext.launch {
            _notifications.value = notificationsRepository.getNotifications()
        }
    }

    fun removeNotification(id: Int) {
        coroutineContext.launch {
            _isRemoved.value = notificationsRepository.removeNotification(id)
        }
    }

    fun acceptInvite(code: String,
                     detailsId: Int,
                     teamId: Int) {
        coroutineContext.launch {
            _isAccepted.value = teamRepository.acceptInvite(code, detailsId, teamId)
        }
    }
}