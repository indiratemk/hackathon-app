package space.platform.hackathon.domain.participants

import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.util.state.State

interface IParticipantsRepository {

    suspend fun register(id: Int, type: Int): State<Result<Boolean, Unit>>

    suspend fun unregister(id: Int): State<Result<Boolean, Unit>>

    suspend fun confirmParticipation(hackathonId: Int,
                                     userId: Int): State<Result<Boolean, Unit>>

    suspend fun getParticipatedHackathons(userId: Int): State<Result<List<Hackathon>, Unit>>

    suspend fun getCurrent(hackathonId: Int): State<Result<Participant, Unit>>

    suspend fun leaveTeam(hackathonId: Int, userId: Int): State<Result<Boolean, Unit>>
}