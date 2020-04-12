package com.example.hackathon.presentation.hackathon.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.domain.participants.ParticipantsRepository
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonRegistrationViewModel(private val participantsRepository: ParticipantsRepository) : BaseViewModel() {

    private val _isRegistered = MutableLiveData<State<Result<Boolean, Unit>>>()

    val isRegistered: LiveData<State<Result<Boolean, Unit>>>
        get() = _isRegistered

    fun register(id: Int) {
        coroutineContext.launch {
            _isRegistered.value = State.Loading(true)
            _isRegistered.value = participantsRepository.register(id)
            _isRegistered.value = State.Loading(false)
        }
    }
}