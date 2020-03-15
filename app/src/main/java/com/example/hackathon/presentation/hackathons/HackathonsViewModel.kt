package com.example.hackathon.presentation.hackathons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.base.BaseViewModel
import com.example.hackathon.data.Paging
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonsViewModel(private val hackathonRepository: HackathonRepository) : BaseViewModel() {

    private val _hackathons = MutableLiveData<State<Paging<Hackathon>>>()
    private val _search = MutableLiveData<State<Paging<Hackathon>>>()

    val hackathons: LiveData<State<Paging<Hackathon>>>
        get() = _hackathons

    val search: LiveData<State<Paging<Hackathon>>>
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