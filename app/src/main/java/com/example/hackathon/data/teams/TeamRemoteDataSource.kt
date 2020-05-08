package com.example.hackathon.data.teams

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Team
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.util.response.ApiResponse

class TeamRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun inviteParticipant(receiverId: Int, teamId: Int): ApiResponse<Result<Notification, Unit>> {
        return getResponse { hackathonApi.inviteParticipant(receiverId, teamId) }
    }

    suspend fun createTeam(hackathonId: Int, title: String): ApiResponse<Result<Team, Unit>> {
        return getResponse { hackathonApi.createTeam(hackathonId, title) }
    }

    suspend fun removeTeam(teamId: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.removeTeam(teamId) }
    }

    suspend fun kickUser(teamId: Int, userId: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.kickUser(teamId, userId) }
    }
}