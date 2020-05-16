package space.platform.hackathon.presentation.qr_scanner

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.domain.participants.ParticipantsRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch

class QRScannerViewModel(private val participantsRepository: ParticipantsRepository) : BaseViewModel() {

    private val _qrParams = MutableLiveData<State<Result<Boolean, Unit>>>()

    val qrParams: LiveData<State<Result<Boolean, Unit>>>
        get() = _qrParams

    fun confirmParticipation(hackathonId: Int,
                             userId: Int) {
        coroutineContext.launch {
            _qrParams.value = State.Loading(true)
            _qrParams.value = participantsRepository.confirmParticipation(hackathonId, userId)
            _qrParams.value = State.Loading(false)
        }
    }
}