package com.example.hackathon.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.base.BaseViewModel
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.domain.AuthRepository
import com.example.hackathon.util.State
import kotlinx.coroutines.launch

class SignUpViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    private val _user = MutableLiveData<State<User>>()

    val user: LiveData<State<User>>
        get() = _user

    fun signUp(login: String,
               email: String,
               password: String) {
        coroutineContext.launch {
            _user.value = State.Loading()
            _user.value = authRepository.signUp(login, email, password)
        }
    }
}