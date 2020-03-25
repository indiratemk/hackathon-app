package com.example.hackathon.presentation.profile

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.domain.user.UserRepository
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class ProfileViewModel(private val userRepository: UserRepository) : BaseViewModel() {

    private val _user = MutableLiveData<State<SuccessResult<User, Unit>>>()

    val user: LiveData<State<SuccessResult<User, Unit>>>
        get() = _user

    init {
        getUser()
    }

    private fun getUser() {
        coroutineContext.launch {
            _user.value = State.Loading(true)
            _user.value = userRepository.getUser()
            _user.value = State.Loading(false)
        }
    }
}