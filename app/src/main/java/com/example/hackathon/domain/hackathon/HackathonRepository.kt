package com.example.hackathon.domain.hackathon

import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.hackathon.HackathonRemoteDataSource
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.util.state.State

class HackathonRepository(private val hackathonRemoteDataSource: HackathonRemoteDataSource) : BaseRepository(),
    IHackathonRepository {

    override suspend fun getHackathons(): State<Result<List<Hackathon>, Paging>> {
        return handleState { hackathonRemoteDataSource.getHackathons() }
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

    override suspend fun register(id: Int): State<Result<Boolean, Unit>> {
        return handleState { hackathonRemoteDataSource.register(id) }
    }

    override suspend fun unregister(id: Int): State<Result<Boolean, Unit>> {
        return handleState { hackathonRemoteDataSource.unregister(id) }
    }
}