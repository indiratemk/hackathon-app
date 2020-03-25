package com.example.hackathon.domain.user

import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.user.UserRemoteDataStore
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State

class UserRepository(private val userRemoteDataStore: UserRemoteDataStore) : BaseRepository(), IUserRepository {

    override suspend fun getUser(): State<SuccessResult<User, Unit>> {
        return handleState { userRemoteDataStore.getUser() }
    }
}