package space.platform.hackathon.presentation.hackathon.participants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.data.notifications.model.Notification
import space.platform.hackathon.domain.hackathon.HackathonRepository
import space.platform.hackathon.domain.team.TeamRepository
import space.platform.hackathon.domain.user.UserRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class ParticipantsViewModel(private val hackathonRepository: HackathonRepository,
                            private val userRepository: UserRepository,
                            private val teamRepository: TeamRepository) : BaseViewModel() {

    private val _participants = MutableLiveData<State<Result<List<Participant>, Unit>>>()
    private val _currentUser = MutableLiveData<Participant?>()
    private val _notification = MutableLiveData<State<Result<Notification, Unit>>>()

    val participants: LiveData<State<Result<List<Participant>, Unit>>>
        get() = _participants

    val currentUser: LiveData<Participant?>
        get() = _currentUser

    val notification: LiveData<State<Result<Notification, Unit>>>
        get() = _notification

    fun getParticipants(id: Int) {
        coroutineContext.launch {
            _participants.value = State.Loading(true)
            val participantsState: State<Result<List<Participant>, Unit>> = hackathonRepository.getParticipants(id)
            participantsState.result?.let {result ->
                _currentUser.value = result.data.find { participant -> participant.userId == userRepository.getCurrentUserId() }
            }
            _participants.value = participantsState
            _participants.value = State.Loading(false)
        }
    }

    fun inviteParticipant(receiverId: Int, teamId: Int) {
        coroutineContext.launch {
            _notification.value = teamRepository.inviteParticipant(receiverId, teamId)
        }
    }
}