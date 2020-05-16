package space.platform.hackathon.domain.auth

import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.util.state.State

interface IAuthRepository {

    suspend fun signUp(login: String,
                       email: String,
                       password: String): State<Result<User, Unit>>

    suspend fun signIn(email: String,
                       password: String): State<Result<User, Unit>>

    suspend fun logout(): State<Result<Boolean, Unit>>
}