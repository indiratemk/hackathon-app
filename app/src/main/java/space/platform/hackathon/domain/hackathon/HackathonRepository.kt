package space.platform.hackathon.domain.hackathon

import space.platform.hackathon.data.base.model.Paging
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.HackathonRemoteDataSource
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.domain.base.BaseRepository
import space.platform.hackathon.util.state.State

class HackathonRepository(private val hackathonRemoteDataSource: HackathonRemoteDataSource) : BaseRepository(),
    IHackathonRepository {

    override suspend fun getHackathons(page: Int): State<Result<List<Hackathon>, Paging>> {
        return handleState { hackathonRemoteDataSource.getHackathons(page) }
    }

    override suspend fun searchHackathons(query: String): State<Result<List<Hackathon>, Paging>> {
        return handleState { hackathonRemoteDataSource.searchHackathons(query) }
    }

    override suspend fun getHackathon(id: Int): State<Result<Hackathon, Unit>> {
        return handleState { hackathonRemoteDataSource.getHackathon(id) }
    }

    override suspend fun checkParticipation(id: Int): State<Result<Boolean, Unit>> {
        return handleState { hackathonRemoteDataSource.checkParticipation(id) }
    }

    override suspend fun getParticipants(id: Int): State<Result<List<Participant>, Unit>> {
        return handleState { hackathonRemoteDataSource.getParticipants(id) }
    }

    override suspend fun getTeams(id: Int): State<Result<List<Team>, Unit>> {
        return handleState { hackathonRemoteDataSource.getTeams(id) }
    }
}