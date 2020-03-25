package com.example.hackathon.data.hackathon

import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.response.ApiResponse
import com.example.hackathon.data.base.model.SuccessResult

class HackathonRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getHackathons(): ApiResponse<SuccessResult<List<Hackathon>, Paging>> {
        return getResponse { hackathonApi.getHackathons() }
    }

    suspend fun searchHackathons(query: String): ApiResponse<SuccessResult<List<Hackathon>, Paging>> {
        return getResponse { hackathonApi.searchHackathons(query) }
    }

    suspend fun getHackathon(id: Int): ApiResponse<SuccessResult<Hackathon, Unit>> {
        return getResponse { hackathonApi.getHackathon(id) }
    }

    suspend fun checkParticipation(id: Int): ApiResponse<SuccessResult<Boolean, Unit>> {
        return getResponse { hackathonApi.checkParticipation(id) }
    }

    suspend fun register(id: Int): ApiResponse<SuccessResult<Boolean, Unit>> {
        return getResponse { hackathonApi.register(id) }
    }

    suspend fun unregister(id: Int): ApiResponse<SuccessResult<Boolean, Unit>> {
        return getResponse { hackathonApi.unregister(id) }
    }
}