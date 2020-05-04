package com.example.hackathon.data.teams

import com.example.hackathon.data.HackathonApi
import com.example.hackathon.data.base.BaseRemoteDataSource
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.user.model.Notification
import com.example.hackathon.util.response.ApiResponse

class TeamRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun inviteParticipant(receiverId: Int, teamId: Int): ApiResponse<Result<Notification, Unit>> {
        return getResponse { hackathonApi.inviteParticipant(receiverId, teamId) }
    }
}