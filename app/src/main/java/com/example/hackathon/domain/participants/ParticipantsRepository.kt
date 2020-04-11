package com.example.hackathon.domain.participants

import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.participants.ParticipantsRemoteDataSource
import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.util.state.State

class ParticipantsRepository(private val participantsRemoteDataSource: ParticipantsRemoteDataSource) : BaseRepository(),
    IParticipantsRepository {

    override suspend fun register(id: Int): State<Result<Boolean, Unit>> {
        return handleState { participantsRemoteDataSource.register(id) }
    }

    override suspend fun unregister(id: Int): State<Result<Boolean, Unit>> {
        return handleState { participantsRemoteDataSource.unregister(id) }
    }

    override suspend fun confirmParticipation(
        hackathonId: Int,
        userId: Int): State<Result<Boolean, Unit>> {
        return handleState { participantsRemoteDataSource.confirmParticipation(hackathonId, userId) }
    }

    override suspend fun getParticipatesInHackathons(userId: Int): State<Result<List<Hackathon>, Unit>> {
        return handleState { participantsRemoteDataSource.getParticipatesInHackathons(userId) }
    }
}