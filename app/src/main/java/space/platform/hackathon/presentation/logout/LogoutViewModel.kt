package space.platform.hackathon.presentation.logout

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.domain.auth.AuthRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class LogoutViewModel(private val authRepository: AuthRepository) : BaseViewModel() {

    private val _logout = MutableLiveData<State<Result<Boolean, Unit>>>()

    val logout: LiveData<State<Result<Boolean, Unit>>>
        get() = _logout

    fun logout() {
        coroutineContext.launch {
            _logout.value = State.Loading(true)
            _logout.value = authRepository.logout()
            _logout.value = State.Loading(false)
        }
    }
}