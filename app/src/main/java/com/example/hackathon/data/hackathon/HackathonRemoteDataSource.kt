package com.example.hackathon.data.hackathon

import com.example.hackathon.base.BaseRemoteDataSource
import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.Paging
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.ApiResponse

class HackathonRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getHackathons(): ApiResponse<Paging<Hackathon>> {
        return getResponse { hackathonApi.getHackathons() }
    }

    suspend fun searchHackathons(query: String): ApiResponse<Paging<Hackathon>> {
        return getResponse { hackathonApi.searchHackathons(query) }
    }

    suspend fun getHackathon(id: Int): ApiResponse<Hackathon> {
        return getResponse { hackathonApi.getHackathon(id) }
    }
}