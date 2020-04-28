package com.example.hackathon.data.hackathon

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.response.ApiResponse

class HackathonRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getHackathons(page: Int): ApiResponse<Result<List<Hackathon>, Paging>> {
        return getResponse { hackathonApi.getHackathons(page) }
    }

    suspend fun searchHackathons(query: String): ApiResponse<Result<List<Hackathon>, Paging>> {
        return getResponse { hackathonApi.searchHackathons(query) }
    }

    suspend fun getHackathon(id: Int): ApiResponse<Result<Hackathon, Unit>> {
        return getResponse { hackathonApi.getHackathon(id) }
    }

    suspend fun checkParticipation(id: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.checkParticipation(id) }
    }

    suspend fun getParticipants(id: Int): ApiResponse<Result<List<User>, Unit>> {
        return getResponse { hackathonApi.getParticipants(id) }
    }
}