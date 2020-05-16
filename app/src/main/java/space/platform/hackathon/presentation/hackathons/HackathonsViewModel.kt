package space.platform.hackathon.presentation.hackathons

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import space.platform.hackathon.data.base.model.Paging
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.domain.hackathon.HackathonRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State
import kotlinx.coroutines.launch
import kotlin.math.roundToInt

class HackathonsViewModel(private val hackathonRepository: HackathonRepository) : BaseViewModel() {

    private val _hackathons = MutableLiveData<State<Result<List<Hackathon>, Paging>>>()
    private val _search = MutableLiveData<State<Result<List<Hackathon>, Paging>>>()
    private var pageToLoad = 1
    private var totalPages = 1

    val hackathons: LiveData<State<Result<List<Hackathon>, Paging>>>
        get() = _hackathons

    val search: LiveData<State<Result<List<Hackathon>, Paging>>>
        get() = _search

    init {
        getHackathons(true)
    }

    fun getHackathons(isRefreshed: Boolean) {
        if (isRefreshed) {
            pageToLoad = 1
        } else {
            if (pageToLoad < totalPages) {
                pageToLoad += 1
            } else {
                return
            }
        }
        coroutineContext.launch {
            _hackathons.value = State.Loading(true)
            val hackathonsState: State<Result<List<Hackathon>, Paging>> = hackathonRepository.getHackathons(pageToLoad)
            hackathonsState.result?.let { result ->
                totalPages = (result.meta.total / result.meta.size.toDouble()).roundToInt()
            }
            _hackathons.value = hackathonsState
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