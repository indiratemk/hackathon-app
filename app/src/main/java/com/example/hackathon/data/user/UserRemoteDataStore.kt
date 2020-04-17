package com.example.hackathon.data.user

import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.response.ApiResponse
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon

class UserRemoteDataStore(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getUser(): ApiResponse<Result<User, Unit>> {
        return getResponse { hackathonApi.getUser() }
    }

    suspend fun getPastHackathons(userId: Int): ApiResponse<Result<List<Hackathon>, Unit>> {
        return getResponse { hackathonApi.getPastHackathons(userId) }
    }
}