package space.platform.hackathon.presentation.hackathon.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.domain.hackathon.HackathonRepository
import space.platform.hackathon.domain.participants.ParticipantsRepository
import space.platform.hackathon.domain.team.TeamRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class TeamsViewModel(private val hackathonRepository: HackathonRepository,
                     private val participantsRepository: ParticipantsRepository,
                     private val teamRepository: TeamRepository) : BaseViewModel() {

    private val _teams = MutableLiveData<State<Result<List<Team>, Unit>>>()
    private val _current = MutableLiveData<State<Result<Participant, Unit>>>()
    private val _newTeam = MutableLiveData<State<Result<Team, Unit>>>()
    private val _isRemoved = MutableLiveData<State<Result<Boolean, Unit>>>()
    private val _isKicked = MutableLiveData<State<Result<Boolean, Unit>>>()
    private val _isLeft = MutableLiveData<State<Result<Boolean, Unit>>>()

    val teams: LiveData<State<Result<List<Team>, Unit>>>
        get() = _teams

    val current: LiveData<State<Result<Participant, Unit>>>
        get() = _current

    val newTeam: LiveData<State<Result<Team, Unit>>>
        get() = _newTeam

    val isRemoved: LiveData<State<Result<Boolean, Unit>>>
        get() = _isRemoved

    val isKicked: LiveData<State<Result<Boolean, Unit>>>
        get() = _isKicked

    val isLeft: LiveData<State<Result<Boolean, Unit>>>
        get() = _isLeft

    fun getTeams(hackathonId: Int) {
        coroutineContext.launch {
            _teams.value = State.Loading(true)
            _current.value = participantsRepository.getCurrent(hackathonId)
            _teams.value = hackathonRepository.getTeams(hackathonId)
            _teams.value = State.Loading(false)
        }
    }

    fun createTeam(hackathonId: Int, title: String) {
        coroutineContext.launch {
            _newTeam.value = teamRepository.createTeam(hackathonId, title)
        }
    }

    fun removeTeam(teamId: Int) {
        coroutineContext.launch {
            _isRemoved.value = teamRepository.removeTeam(teamId)
        }
    }

    fun kickUser(teamId: Int, userId: Int) {
        coroutineContext.launch {
            _isKicked.value = teamRepository.kickUser(teamId, userId)
        }
    }

    fun leaveTeam(hackathonId: Int, userId: Int) {
        coroutineContext.launch {
            _isLeft.value = participantsRepository.leaveTeam(hackathonId, userId)
        }
    }
}