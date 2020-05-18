package space.platform.hackathon.domain.tasks

import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.tasks.Task
import space.platform.hackathon.data.tasks.TasksRemoteDataSource
import space.platform.hackathon.domain.base.BaseRepository
import space.platform.hackathon.util.state.State

class TasksRepository(private val tasksRemoteDataSource: TasksRemoteDataSource) : BaseRepository(),
    ITasksRepository{

    override suspend fun getTasks(id: Int): State<Result<List<Task>, Unit>> {
        return handleState { tasksRemoteDataSource.getTasks(id) }
    }
}