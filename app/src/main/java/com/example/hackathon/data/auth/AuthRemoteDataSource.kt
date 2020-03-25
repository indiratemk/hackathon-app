package com.example.hackathon.data.auth

import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.response.ApiResponse
import com.example.hackathon.data.base.model.SuccessResult

class AuthRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun signUp(login: String,
               email: String,
               password: String): ApiResponse<SuccessResult<User, Unit>> {
        return getResponse { hackathonApi.signUp(login, email, password) }
    }

    suspend fun signIn(email: String,
                       password: String): ApiResponse<SuccessResult<User, Unit>> {
        return getResponse { hackathonApi.signIn(email, password) }
    }

    suspend fun logout(): ApiResponse<SuccessResult<Boolean, Unit>> {
        return getResponse { hackathonApi.logout() }
    }
}