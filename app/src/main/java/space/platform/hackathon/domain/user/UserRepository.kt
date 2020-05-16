package space.platform.hackathon.domain.user

import space.platform.hackathon.data.auth.model.User
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.data.user.UserLocalDataSource
import space.platform.hackathon.data.user.UserRemoteDataSource
import space.platform.hackathon.domain.base.BaseRepository
import space.platform.hackathon.util.state.State

class UserRepository(private val userRemoteDataSource: UserRemoteDataSource,
                     private val userLocalDataSource: UserLocalDataSource
) : BaseRepository(), IUserRepository {

    override suspend fun getCurrentUser(): State<Result<User, Unit>> {
        return handleState { userRemoteDataSource.getCurrentUser() }
    }

    override suspend fun getUserByEmail(email: String): State<Result<User, Unit>> {
        return handleState { userRemoteDataSource.getUserByEmail(email) }
    }

    override suspend fun getOwnHackathons(userId: Int): State<Result<List<Hackathon>, Unit>> {
        return handleState { userRemoteDataSource.getOwnHackathons(userId) }
    }

    override fun getCurrentUserId(): Int {
        return userLocalDataSource.getAuthorizedUserId()
    }
}