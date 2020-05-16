package space.platform.hackathon.data.teams

import space.platform.hackathon.data.HackathonApi
import space.platform.hackathon.data.base.BaseRemoteDataSource
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.data.notifications.model.Notification
import space.platform.hackathon.util.response.ApiResponse

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

    suspend fun acceptInvite(code: String, detailsId: Int, teamId: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.acceptInvite(code, detailsId, teamId) }
    }
}