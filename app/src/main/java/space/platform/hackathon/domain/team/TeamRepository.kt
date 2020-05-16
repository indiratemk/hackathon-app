package space.platform.hackathon.domain.team

import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.data.teams.TeamRemoteDataSource
import space.platform.hackathon.data.notifications.model.Notification
import space.platform.hackathon.domain.base.BaseRepository
import space.platform.hackathon.util.state.State

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

    override suspend fun acceptInvite(code: String, detailsId: Int, teamId: Int): State<Result<Boolean, Unit>> {
        return handleState { teamRemoteDataSource.acceptInvite(code, detailsId, teamId) }
    }
}