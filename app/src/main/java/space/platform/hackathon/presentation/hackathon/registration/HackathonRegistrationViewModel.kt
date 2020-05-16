package space.platform.hackathon.presentation.hackathon.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.domain.participants.ParticipantsRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonRegistrationViewModel(private val participantsRepository: ParticipantsRepository) : BaseViewModel() {

    private val _isRegistered = MutableLiveData<State<Result<Boolean, Unit>>>()

    val isRegistered: LiveData<State<Result<Boolean, Unit>>>
        get() = _isRegistered

    fun register(id: Int, type: Int) {
        coroutineContext.launch {
            _isRegistered.value = State.Loading(true)
            _isRegistered.value = participantsRepository.register(id, type)
            _isRegistered.value = State.Loading(false)
        }
    }
}