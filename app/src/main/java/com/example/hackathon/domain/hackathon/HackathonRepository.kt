package com.example.hackathon.domain.hackathon

import com.example.hackathon.domain.base.BaseRepository
import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.hackathon.HackathonRemoteDataSource
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State

class HackathonRepository(private val hackathonRemoteDataSource: HackathonRemoteDataSource) : BaseRepository(),
    IHackathonRepository {

    override suspend fun getHackathons(): State<SuccessResult<List<Hackathon>, Paging>> {
        return handleState { hackathonRemoteDataSource.getHackathons() }
    }

    override suspend fun searchHackathons(query: String): State<SuccessResult<List<Hackathon>, Paging>> {
        return handleState { hackathonRemoteDataSource.searchHackathons(query) }
    }

    override suspend fun getHackathon(id: Int): State<SuccessResult<Hackathon, Unit>> {
        return handleState { hackathonRemoteDataSource.getHackathon(id) }
    }

    override suspend fun checkParticipation(id: Int): State<SuccessResult<Boolean, Unit>> {
        return handleState { hackathonRemoteDataSource.checkParticipation(id) }
    }

    override suspend fun register(id: Int): State<SuccessResult<Boolean, Unit>> {
        return handleState { hackathonRemoteDataSource.register(id) }
    }

    override suspend fun unregister(id: Int): State<SuccessResult<Boolean, Unit>> {
        return handleState { hackathonRemoteDataSource.unregister(id) }
    }
}