package com.example.hackathon.data.auth

import com.example.hackathon.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.ApiResponse

class AuthRemoteDataSource(private val hackathonApi: HackathonApi): BaseRemoteDataSource() {

    suspend fun signUp(login: String,
               email: String,
               password: String): ApiResponse<User> {
        return getResponse { hackathonApi.signUp(login, email, password) }
    }

    suspend fun signIn(email: String,
                       password: String): ApiResponse<User> {
        return getResponse { hackathonApi.signIn(email, password) }
    }
}