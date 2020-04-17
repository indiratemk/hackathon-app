package com.example.hackathon.data.participants

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.response.ApiResponse

class ParticipantsRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun register(id: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.register(id) }
    }

    suspend fun unregister(id: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.unregister(id) }
    }

    suspend fun confirmParticipation(hackathonId: Int,
                                     userId: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.confirmParticipation(hackathonId, userId) }
    }

    suspend fun getCurrentHackathons(userId: Int): ApiResponse<Result<List<Hackathon>, Unit>> {
        return getResponse { hackathonApi.getCurrentHackathons(userId) }
    }
}