package com.example.hackathon.presentation.hackathon.teams

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Team
import com.example.hackathon.data.participants.model.Participant
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.domain.participants.ParticipantsRepository
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class TeamsViewModel(private val hackathonRepository: HackathonRepository,
                     private val participantsRepository: ParticipantsRepository) : BaseViewModel() {

    private val _teams = MutableLiveData<State<Result<List<Team>, Unit>>>()
    private val _current = MutableLiveData<State<Result<Participant, Unit>>>()

    val teams: LiveData<State<Result<List<Team>, Unit>>>
        get() = _teams

    val current: LiveData<State<Result<Participant, Unit>>>
        get() = _current

    fun getTeam(hackathonId: Int) {
        coroutineContext.launch {
            _teams.value = State.Loading(true)
            _current.value = participantsRepository.getCurrent(hackathonId)
            _teams.value = hackathonRepository.getTeams(hackathonId)
            _teams.value = State.Loading(false)
        }
    }
}