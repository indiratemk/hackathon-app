package com.example.hackathon.data.participants

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.participants.model.Participant
import com.example.hackathon.util.response.ApiResponse

class ParticipantsRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun register(id: Int, type: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.register(id, type) }
    }

    suspend fun unregister(id: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.unregister(id) }
    }

    suspend fun confirmParticipation(hackathonId: Int,
                                     userId: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.confirmParticipation(hackathonId, userId) }
    }

    suspend fun getParticipatedHackathons(userId: Int): ApiResponse<Result<List<Hackathon>, Unit>> {
        return getResponse { hackathonApi.getParticipatedHackathons(userId) }
    }

    suspend fun getCurrent(hackathonId: Int): ApiResponse<Result<Participant, Unit>> {
        return getResponse { hackathonApi.getCurrent(hackathonId) }
    }

    suspend fun leaveTeam(hackathonId: Int,
                          userId: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.leaveTeam(hackathonId, userId) }
    }
}