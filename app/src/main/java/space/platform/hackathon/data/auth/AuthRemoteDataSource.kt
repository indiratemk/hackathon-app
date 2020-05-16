package space.platform.hackathon.data.auth

import space.platform.hackathon.data.base.BaseRemoteDataSource
import space.platform.hackathon.data.HackathonApi
import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.util.response.ApiResponse
import space.platform.hackathon.data.base.model.Result

class AuthRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun signUp(login: String,
               email: String,
               password: String): ApiResponse<Result<User, Unit>> {
        return getResponse { hackathonApi.signUp(login, email, password) }
    }

    suspend fun signIn(email: String,
                       password: String): ApiResponse<Result<User, Unit>> {
        return getResponse { hackathonApi.signIn(email, password) }
    }

    suspend fun logout(): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.logout() }
    }
}