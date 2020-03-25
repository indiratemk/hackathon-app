package com.example.hackathon.presentation.hackathon.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonRegistrationViewModel(private val hackathonRepository: HackathonRepository): BaseViewModel() {

    private val _isRegistered = MutableLiveData<State<SuccessResult<Boolean, Unit>>>()

    val isRegistered: LiveData<State<SuccessResult<Boolean, Unit>>>
        get() = _isRegistered

    fun register(id: Int) {
        coroutineContext.launch {
            _isRegistered.value = State.Loading(true)
            _isRegistered.value = hackathonRepository.register(id)
            _isRegistered.value = State.Loading(false)
        }
    }
}