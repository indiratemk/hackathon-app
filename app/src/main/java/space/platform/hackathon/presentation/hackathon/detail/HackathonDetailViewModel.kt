package space.platform.hackathon.presentation.hackathon.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.domain.hackathon.HackathonRepository
import space.platform.hackathon.domain.participants.ParticipantsRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class HackathonDetailViewModel(private val hackathonRepository: HackathonRepository,
                               private val participantsRepository: ParticipantsRepository) : BaseViewModel() {

    private val _hackathon = MutableLiveData<State<Result<Hackathon, Unit>>>()
    private val _isParticipate = MutableLiveData<State<Result<Boolean, Unit>>>()
    private val _unregister = MutableLiveData<State<Result<Boolean, Unit>>>()

    val hackathon: LiveData<State<Result<Hackathon, Unit>>>
        get() = _hackathon

    val isParticipate: LiveData<State<Result<Boolean, Unit>>>
        get() = _isParticipate

    val unregister: LiveData<State<Result<Boolean, Unit>>>
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
            _unregister.value = participantsRepository.unregister(id)
        }
    }
}