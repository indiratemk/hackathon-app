package space.platform.hackathon.domain.user

import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.util.state.State

interface IUserRepository {

    suspend fun getCurrentUser(): State<Result<User, Unit>>

    suspend fun getUserByEmail(email: String): State<Result<User, Unit>>

    suspend fun getOwnHackathons(userId: Int): State<Result<List<Hackathon>, Unit>>

    fun getCurrentUserId(): Int
}