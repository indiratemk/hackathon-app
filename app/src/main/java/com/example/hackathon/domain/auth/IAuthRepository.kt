package com.example.hackathon.domain.auth

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State

interface IAuthRepository {

    suspend fun signUp(login: String,
                       email: String,
                       password: String): State<SuccessResult<User, Unit>>

    suspend fun signIn(email: String,
                       password: String): State<SuccessResult<User, Unit>>

    suspend fun logout(): State<SuccessResult<Boolean, Unit>>
}