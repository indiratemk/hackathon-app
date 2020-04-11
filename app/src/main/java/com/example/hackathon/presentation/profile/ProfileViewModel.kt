package com.example.hackathon.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.domain.user.UserRepository
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.domain.participants.ParticipantsRepository
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository,
                       private val participantsRepository: ParticipantsRepository) : BaseViewModel() {

    private val _user = MutableLiveData<State<Result<User, Unit>>>()
    private val _participatesInHackathons = MutableLiveData<State<Result<List<Hackathon>, Unit>>>()

    val user: LiveData<State<Result<User, Unit>>>
        get() = _user
    val participatesInHackathons: LiveData<State<Result<List<Hackathon>, Unit>>>
        get() = _participatesInHackathons

    init {
        getUser()
    }

    fun getUser() {
        coroutineContext.launch {
            _user.value = State.Loading(true)
            _user.value = userRepository.getUser()
            _user.value = State.Loading(false)
        }
    }

    fun getParticipatesInHackathons(userId: Int) {
        coroutineContext.launch {
            _participatesInHackathons.value = participantsRepository.getParticipatesInHackathons(userId)
        }
    }
}