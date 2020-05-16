package space.platform.hackathon.domain.auth

import space.platform.hackathon.domain.base.BaseRepository
import space.platform.hackathon.data.auth.AuthRemoteDataSource
import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.util.state.State

class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource) : BaseRepository(),
    IAuthRepository {

    override suspend fun signUp(login: String, email: String, password: String): State<Result<User, Unit>> {
        return handleState { authRemoteDataSource.signUp(login, email, password) }
    }

    override suspend fun signIn(email: String, password: String): State<Result<User, Unit>> {
        return handleState { authRemoteDataSource.signIn(email, password) }
    }

    override suspend fun logout(): State<Result<Boolean, Unit>> {
        return handleState { authRemoteDataSource.logout() }
    }
}