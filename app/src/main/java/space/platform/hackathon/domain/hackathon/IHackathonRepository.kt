package space.platform.hackathon.domain.hackathon

import space.platform.hackathon.data.base.model.Paging
import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.util.state.State

interface IHackathonRepository {

    suspend fun getHackathons(page: Int): State<Result<List<Hackathon>, Paging>>

    suspend fun searchHackathons(query: String): State<Result<List<Hackathon>, Paging>>

    suspend fun getHackathon(id: Int): State<Result<Hackathon, Unit>>

    suspend fun checkParticipation(id: Int): State<Result<Boolean, Unit>>

    suspend fun getParticipants(id: Int): State<Result<List<Participant>, Unit>>

    suspend fun getTeams(id: Int): State<Result<List<Team>, Unit>>
}