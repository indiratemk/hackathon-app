package com.example.hackathon.data.user

import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.response.ApiResponse
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon

class UserRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getCurrentUser(): ApiResponse<Result<User, Unit>> {
        return getResponse { hackathonApi.getCurrentUser() }
    }

    suspend fun getOwnHackathons(userId: Int): ApiResponse<Result<List<Hackathon>, Unit>> {
        return getResponse { hackathonApi.getOwnHackathons(userId) }
    }

    suspend fun getUserByEmail(email: String): ApiResponse<Result<User, Unit>> {
        return getResponse { hackathonApi.getUserByEmail(email) }
    }
}