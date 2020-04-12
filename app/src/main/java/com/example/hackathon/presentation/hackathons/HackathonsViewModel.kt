package com.example.hackathon.presentation.hackathons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonsViewModel(private val hackathonRepository: HackathonRepository) : BaseViewModel() {

    private val _hackathons = MutableLiveData<State<Result<List<Hackathon>, Paging>>>()
    private val _search = MutableLiveData<State<Result<List<Hackathon>, Paging>>>()

    val hackathons: LiveData<State<Result<List<Hackathon>, Paging>>>
        get() = _hackathons

    val search: LiveData<State<Result<List<Hackathon>, Paging>>>
        get() = _search

    init {
        getHackathons()
    }

    fun getHackathons() {
        coroutineContext.launch {
            _hackathons.value = State.Loading(true)
            _hackathons.value = hackathonRepository.getHackathons()
            _hackathons.value = State.Loading(false)
        }
    }

    fun searchHackathons(query: String) {
        coroutineContext.launch {
            _search.value = State.Loading(true)
            _search.value = hackathonRepository.searchHackathons(query)
            _search.value = State.Loading(false)
        }
    }
}