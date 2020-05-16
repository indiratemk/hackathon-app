package space.platform.hackathon.domain.participants

import space.platform.hackathon.data.base.model.Result
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.data.participants.ParticipantsRemoteDataSource
import space.platform.hackathon.data.participants.model.Participant
import space.platform.hackathon.domain.base.BaseRepository
import space.platform.hackathon.util.state.State

class ParticipantsRepository(private val participantsRemoteDataSource: ParticipantsRemoteDataSource) : BaseRepository(),
    IParticipantsRepository {

    override suspend fun register(id: Int, type: Int): State<Result<Boolean, Unit>> {
        return handleState { participantsRemoteDataSource.register(id, type) }
    }

    override suspend fun unregister(id: Int): State<Result<Boolean, Unit>> {
        return handleState { participantsRemoteDataSource.unregister(id) }
    }

    override suspend fun confirmParticipation(
        hackathonId: Int,
        userId: Int): State<Result<Boolean, Unit>> {
        return handleState { participantsRemoteDataSource.confirmParticipation(hackathonId, userId) }
    }

    override suspend fun getParticipatedHackathons(userId: Int): State<Result<List<Hackathon>, Unit>> {
        return handleState { participantsRemoteDataSource.getParticipatedHackathons(userId) }
    }

    override suspend fun getCurrent(hackathonId: Int): State<Result<Participant, Unit>> {
        return handleState { participantsRemoteDataSource.getCurrent(hackathonId) }
    }

    override suspend fun leaveTeam(hackathonId: Int, userId: Int): State<Result<Boolean, Unit>> {
        return handleState { participantsRemoteDataSource.leaveTeam(hackathonId, userId) }
    }
}