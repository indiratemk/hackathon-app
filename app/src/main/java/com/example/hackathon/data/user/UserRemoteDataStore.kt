package com.example.hackathon.data.user

import com.example.hackathon.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.ApiResponse

class UserRemoteDataStore(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getUser(): ApiResponse<User> {
        return getResponse { hackathonApi.getUser() }
    }
}