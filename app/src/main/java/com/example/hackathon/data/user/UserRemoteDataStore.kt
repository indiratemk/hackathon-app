package com.example.hackathon.data.user

import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.response.ApiResponse
import com.example.hackathon.data.base.model.SuccessResult

class UserRemoteDataStore(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getUser(): ApiResponse<SuccessResult<User, Unit>> {
        return getResponse { hackathonApi.getUser() }
    }
}