package com.example.hackathon.domain.user

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.user.UserLocalDataSource
import com.example.hackathon.data.user.UserRemoteDataSource
import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.util.state.State

class UserRepository(private val userRemoteDataSource: UserRemoteDataSource,
                     private val userLocalDataSource: UserLocalDataSource) : BaseRepository(), IUserRepository {

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