package space.platform.hackathon.data.tasks

import space.platform.hackathon.data.HackathonApi
import space.platform.hackathon.data.base.BaseRemoteDataSource
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.util.response.ApiResponse

class TasksRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getTasks(id: Int): ApiResponse<Result<List<Task>, Unit>> {
        return getResponse { hackathonApi.getTasks(id) }
    }
}