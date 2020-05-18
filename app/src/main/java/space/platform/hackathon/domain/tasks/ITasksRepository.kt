package space.platform.hackathon.domain.tasks

import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.tasks.Task
import space.platform.hackathon.util.state.State

interface ITasksRepository {

    suspend fun getTasks(id: Int): State<Result<List<Task>, Unit>>
}