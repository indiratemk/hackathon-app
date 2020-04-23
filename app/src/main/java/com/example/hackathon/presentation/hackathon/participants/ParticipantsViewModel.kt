package com.example.hackathon.presentation.hackathon.participants

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.domain.user.UserRepository
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class ParticipantsViewModel(private val hackathonRepository: HackathonRepository,
                            private val userRepository: UserRepository) : BaseViewModel() {

    private val _participants = MutableLiveData<State<Result<List<User>, Unit>>>()
    private val _currentUser = MutableLiveData<User?>()

    val participants: LiveData<State<Result<List<User>, Unit>>>
        get() = _participants

    val currentUser: LiveData<User?>
        get() = _currentUser

    fun getParticipants(id: Int) {
        coroutineContext.launch {
            _participants.value = State.Loading(true)
            val participantsState: State<Result<List<User>, Unit>> = hackathonRepository.getParticipants(id)
            participantsState.result?.let {result ->
                _currentUser.value = result.data.find { participant -> participant.id == userRepository.getCurrentUserId() }
            }
            _participants.value = participantsState
            _participants.value = State.Loading(false)
        }
    }
}