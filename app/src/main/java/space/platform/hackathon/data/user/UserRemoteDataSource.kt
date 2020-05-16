package space.platform.hackathon.data.user

import space.platform.hackathon.data.base.BaseRemoteDataSource
import space.platform.hackathon.data.HackathonApi
import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.util.response.ApiResponse
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon

class UserRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getCurrentUser(): ApiResponse<Result<User, Unit>> {
        return getResponse { hackathonApi.getCurrentUser() }
    }

    suspend fun getOwnHackathons(userId: Int): ApiResponse<Result<List<Hackathon>, Unit>> {
        return getResponse { hackathonApi.getOwnHackathons(userId) }
    }

    suspend fun getUserByEmail(email: String): ApiResponse<Result<User, Unit>> {
        return getResponse { hackathonApi.getUserByEmail(email) }
    }
}