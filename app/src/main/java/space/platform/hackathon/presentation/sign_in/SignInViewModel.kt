package space.platform.hackathon.presentation.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.domain.auth.AuthRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class SignInViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    private val _user = MutableLiveData<State<Result<User, Unit>>>()

    val user: LiveData<State<Result<User, Unit>>>
        get() = _user

    fun signIn(email: String, password: String) {
        coroutineContext.launch {
            _user.value = State.Loading(true)
            _user.value = authRepository.signIn(email, password)
            _user.value = State.Loading(false)
        }
    }
}