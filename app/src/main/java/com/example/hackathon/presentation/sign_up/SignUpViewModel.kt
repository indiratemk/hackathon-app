package com.example.hackathon.presentation.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hackathon.presentation.base.BaseViewModel
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.domain.auth.AuthRepository
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State
import kotlinx.coroutines.launch

class SignUpViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    private val _user = MutableLiveData<State<SuccessResult<User, Unit>>>()

    val user: LiveData<State<SuccessResult<User, Unit>>>
        get() = _user

    fun signUp(login: String,
               email: String,
               password: String) {
        coroutineContext.launch {
            _user.value = State.Loading(true)
            _user.value = authRepository.signUp(login, email, password)
            _user.value = State.Loading(false)
        }
    }
}