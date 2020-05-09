package com.example.hackathon.domain.team

import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Team
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.util.state.State

interface ITeamRepository {

    suspend fun inviteParticipant(receiverId: Int, teamId: Int): State<Result<Notification, Unit>>

    suspend fun createTeam(hackathonId: Int, title: String): State<Result<Team, Unit>>

    suspend fun removeTeam(teamId: Int): State<Result<Boolean, Unit>>

    suspend fun kickUser(teamId: Int, userId: Int): State<Result<Boolean, Unit>>

    suspend fun acceptInvite(code: String, detailsId: Int, teamId: Int): State<Result<Boolean, Unit>>
}