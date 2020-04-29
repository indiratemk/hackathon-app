package com.example.hackathon.domain.hackathon

import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.HackathonRemoteDataSource
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.participants.model.Participant
import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.util.state.State

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
}