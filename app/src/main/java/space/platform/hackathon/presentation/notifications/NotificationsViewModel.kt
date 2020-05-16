package space.platform.hackathon.presentation.notifications

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.notifications.model.Notification
import space.platform.hackathon.domain.notifications.NotificationsRepository
import space.platform.hackathon.domain.team.TeamRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class NotificationsViewModel(private val notificationsRepository: NotificationsRepository,
                             private val teamRepository: TeamRepository) :
    BaseViewModel() {

    private val _notifications = MutableLiveData<State<Result<List<Notification>, Unit>>>()
    private val _isRemoved = MutableLiveData<State<Result<Boolean, Unit>>>()
    private val _isAccepted = MutableLiveData<State<Result<Boolean, Unit>>>()
    private val _isSent = MutableLiveData<State<Result<Boolean, Unit>>>()

    val notifications: LiveData<State<Result<List<Notification>, Unit>>>
        get() = _notifications

    val isRemoved: LiveData<State<Result<Boolean, Unit>>>
        get() = _isRemoved

    val isAccepted: LiveData<State<Result<Boolean, Unit>>>
        get() = _isAccepted

    val isSent: LiveData<State<Result<Boolean, Unit>>>
        get() = _isSent

    init {
        getNotifications()
    }

    fun getNotifications() {
        coroutineContext.launch {
            _notifications.value = State.Loading(true)
            _notifications.value = notificationsRepository.getNotifications()
            _notifications.value = State.Loading(false)

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

    fun sendFeedback(hackathonId: Int,
                     message: String,
                     score: Int) {
        coroutineContext.launch {
            _isSent.value = notificationsRepository.sendFeedback(hackathonId, message, score)
        }
    }
}