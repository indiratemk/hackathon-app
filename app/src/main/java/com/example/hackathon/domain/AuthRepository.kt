package com.example.hackathon.domain

import com.example.hackathon.base.BaseRepository
import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.state.State

class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource) : BaseRepository(),
    IAuthRepository {

    override suspend fun signUp(login: String, email: String, password: String): State<User> {
        return handleState { authRemoteDataSource.signUp(login, email, password) }
    }

    override suspend fun signIn(email: String, password: String): State<User> {
        return handleState { authRemoteDataSource.signIn(email, password) }
    }
}