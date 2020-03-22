package com.example.hackathon.presentation.hackathon_detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.base.BaseViewModel
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonDetailViewModel(private val hackathonRepository: HackathonRepository) : BaseViewModel() {

    private val _hackathon = MutableLiveData<State<Hackathon>>()

    val hackathon: LiveData<State<Hackathon>>
        get() = _hackathon

    fun getHackathon(id: Int) {
        coroutineContext.launch {
            _hackathon.value = State.Loading(true)
            _hackathon.value = hackathonRepository.getHackathon(id)
            _hackathon.value = State.Loading(false)
        }
    }
}