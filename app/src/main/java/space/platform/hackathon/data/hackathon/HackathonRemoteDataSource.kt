package space.platform.hackathon.data.hackathon

import space.platform.hackathon.data.HackathonApi
import space.platform.hackathon.data.base.BaseRemoteDataSource
import space.platform.hackathon.data.base.model.Paging
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.util.response.ApiResponse

class HackathonRemoteDataSource(private val hackathonApi: HackathonApi) : BaseRemoteDataSource() {

    suspend fun getHackathons(page: Int): ApiResponse<Result<List<Hackathon>, Paging>> {
        return getResponse { hackathonApi.getHackathons(page) }
    }

    suspend fun searchHackathons(query: String): ApiResponse<Result<List<Hackathon>, Paging>> {
        return getResponse { hackathonApi.searchHackathons(query) }
    }

    suspend fun getHackathon(id: Int): ApiResponse<Result<Hackathon, Unit>> {
        return getResponse { hackathonApi.getHackathon(id) }
    }

    suspend fun checkParticipation(id: Int): ApiResponse<Result<Boolean, Unit>> {
        return getResponse { hackathonApi.checkParticipation(id) }
    }

    suspend fun getParticipants(id: Int): ApiResponse<Result<List<Participant>, Unit>> {
        return getResponse { hackathonApi.getParticipants(id) }
    }

    suspend fun getTeams(id: Int): ApiResponse<Result<List<Team>, Unit>> {
        return getResponse { hackathonApi.getTeams(id) }
    }
}