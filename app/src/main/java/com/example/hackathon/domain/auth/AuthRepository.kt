package com.example.hackathon.domain.auth

import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State

class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource) : BaseRepository(),
    IAuthRepository {

    override suspend fun signUp(login: String, email: String, password: String): State<SuccessResult<User, Unit>> {
        return handleState { authRemoteDataSource.signUp(login, email, password) }
    }

    override suspend fun signIn(email: String, password: String): State<SuccessResult<User, Unit>> {
        return handleState { authRemoteDataSource.signIn(email, password) }
    }

    override suspend fun logout(): State<SuccessResult<Boolean, Unit>> {
        return handleState { authRemoteDataSource.logout() }
    }
}