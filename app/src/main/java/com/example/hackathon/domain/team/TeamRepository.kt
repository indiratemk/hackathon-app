package com.example.hackathon.domain.team

import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.teams.TeamRemoteDataSource
import com.example.hackathon.data.user.model.Notification
import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.util.state.State

class TeamRepository(private val teamRemoteDataSource: TeamRemoteDataSource) : BaseRepository(),
    ITeamRepository {

    override suspend fun inviteParticipant(receiverId: Int, teamId: Int): State<Result<Notification, Unit>> {
        return handleState { teamRemoteDataSource.inviteParticipant(receiverId, teamId) }
    }
}