package com.example.hackathon.domain.user

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.user.UserLocalDataSource
import com.example.hackathon.data.user.UserRemoteDataStore
import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.util.state.State

class UserRepository(private val userRemoteDataStore: UserRemoteDataStore,
                     private val userLocalDataSource: UserLocalDataSource) : BaseRepository(), IUserRepository {

    override suspend fun getCurrentUser(): State<Result<User, Unit>> {
        return handleState { userRemoteDataStore.getCurrentUser() }
    }

    override suspend fun getUserByEmail(email: String): State<Result<User, Unit>> {
        return handleState { userRemoteDataStore.getUserByEmail(email) }
    }

    override suspend fun getOwnHackathons(userId: Int): State<Result<List<Hackathon>, Unit>> {
        return handleState { userRemoteDataStore.getOwnHackathons(userId) }
    }

    override fun getCurrentUserId(): Int {
        return userLocalDataSource.getAuthorizedUserId()
    }
}