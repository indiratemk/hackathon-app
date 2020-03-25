package com.example.hackathon.presentation.hackathon.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonDetailViewModel(private val hackathonRepository: HackathonRepository) : BaseViewModel() {

    private val _hackathon = MutableLiveData<State<SuccessResult<Hackathon, Unit>>>()
    private val _isParticipate = MutableLiveData<State<SuccessResult<Boolean, Unit>>>()
    private val _unregister = MutableLiveData<State<SuccessResult<Boolean, Unit>>>()

    val hackathon: LiveData<State<SuccessResult<Hackathon, Unit>>>
        get() = _hackathon

    val isParticipate: LiveData<State<SuccessResult<Boolean, Unit>>>
        get() = _isParticipate

    val unregister: LiveData<State<SuccessResult<Boolean, Unit>>>
        get() = _unregister

    fun getHackathon(id: Int) {
        coroutineContext.launch {
            _hackathon.value = State.Loading(true)
            _hackathon.value = hackathonRepository.getHackathon(id)
            _hackathon.value = State.Loading(false)
        }
    }

    fun checkParticipation(id: Int) {
        coroutineContext.launch {
            _isParticipate.value = hackathonRepository.checkParticipation(id)
        }
    }

    fun unregister(id: Int) {
        coroutineContext.launch {
            _unregister.value = hackathonRepository.unregister(id)
        }
    }
}