package space.platform.hackathon.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.domain.notifications.NotificationsRepository
import space.platform.hackathon.domain.participants.ParticipantsRepository
import space.platform.hackathon.domain.user.UserRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository,
                       private val participantsRepository: ParticipantsRepository,
                       private val notificationsRepository: NotificationsRepository) : BaseViewModel() {

    private val _user = MutableLiveData<State<Result<User, Unit>>>()
    private val _participatedHackathons = MutableLiveData<State<Result<List<Hackathon>, Unit>>>()
    private val _ownHackathons = MutableLiveData<State<Result<List<Hackathon>, Unit>>>()
    private val _notificationsCount = MutableLiveData<State<Result<HashMap<String, Int>, Unit>>>()

    val user: LiveData<State<Result<User, Unit>>>
        get() = _user

    val participatedHackathons: LiveData<State<Result<List<Hackathon>, Unit>>>
        get() = _participatedHackathons

    val ownHackathons: LiveData<State<Result<List<Hackathon>, Unit>>>
        get() = _ownHackathons

    val notificationsCount: LiveData<State<Result<HashMap<String, Int>, Unit>>>
        get() = _notificationsCount

    fun getCurrentUser() {
        coroutineContext.launch {
            _user.value = State.Loading(true)
            _user.value = userRepository.getCurrentUser()
            _user.value = State.Loading(false)
        }
    }

    fun getUserByEmail(email: String) {
        coroutineContext.launch {
            _user.value = State.Loading(true)
            _user.value = userRepository.getUserByEmail(email)
            _user.value = State.Loading(false)
        }
    }

    fun getParticipatedHackathons(userId: Int) {
        coroutineContext.launch {
            _participatedHackathons.value = participantsRepository.getParticipatedHackathons(userId)
        }
    }

    fun getOwnHackathons(userId: Int) {
        coroutineContext.launch {
            _ownHackathons.value = userRepository.getOwnHackathons(userId)
        }
    }

    fun getNotificationsCount() {
        coroutineContext.launch {
            _notificationsCount.value = notificationsRepository.getNotificationsCount()
        }
    }
}