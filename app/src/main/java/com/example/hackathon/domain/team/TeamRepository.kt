package com.example.hackathon.domain.team

import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Team
import com.example.hackathon.data.teams.TeamRemoteDataSource
import com.example.hackathon.data.user.model.Notification
import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.util.state.State

class TeamRepository(private val teamRemoteDataSource: TeamRemoteDataSource) : BaseRepository(),
    ITeamRepository {

    override suspend fun inviteParticipant(receiverId: Int, teamId: Int): State<Result<Notification, Unit>> {
        return handleState { teamRemoteDataSource.inviteParticipant(receiverId, teamId) }
    }

    override suspend fun createTeam(hackathonId: Int, title: String): State<Result<Team, Unit>> {
        return handleState { teamRemoteDataSource.createTeam(hackathonId, title) }
    }

    override suspend fun removeTeam(teamId: Int): State<Result<Boolean, Unit>> {
        return handleState { teamRemoteDataSource.removeTeam(teamId) }
    }

    override suspend fun kickUser(teamId: Int, userId: Int): State<Result<Boolean, Unit>> {
        return handleState { teamRemoteDataSource.kickUser(teamId, userId) }
    }
}