package com.example.hackathon.domain.team

import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.user.model.Notification
import com.example.hackathon.util.state.State

interface ITeamRepository {

    suspend fun inviteParticipant(receiverId: Int, teamId: Int): State<Result<Notification, Unit>>
}