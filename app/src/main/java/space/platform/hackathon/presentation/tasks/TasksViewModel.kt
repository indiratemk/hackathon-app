package space.platform.hackathon.presentation.tasks

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.launch
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.tasks.Task
import space.platform.hackathon.domain.tasks.TasksRepository
import space.platform.hackathon.presentation.base.BaseViewModel
import space.platform.hackathon.util.state.State

class TasksViewModel(private val tasksRepository: TasksRepository) : BaseViewModel() {

    private val _tasks = MutableLiveData<State<Result<List<Task>, Unit>>>()

    val tasks: LiveData<State<Result<List<Task>, Unit>>>
        get() = _tasks

    fun getTasks(id: Int) {
        coroutineContext.launch {
            _tasks.value = tasksRepository.getTasks(id)
        }
    }
}