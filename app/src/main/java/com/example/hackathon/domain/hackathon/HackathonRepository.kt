package com.example.hackathon.domain.hackathon

import com.example.hackathon.base.BaseRepository
import com.example.hackathon.data.Paging
import com.example.hackathon.data.hackathon.HackathonRemoteDataSource
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.state.State

class HackathonRepository(private val hackathonRemoteDataSource: HackathonRemoteDataSource) : BaseRepository(),
    IHackathonRepository {

    override suspend fun getHackathons(): State<Paging<Hackathon>> {
        return handleState { hackathonRemoteDataSource.getHackathons() }
    }

    override suspend fun searchHackathons(query: String): State<Paging<Hackathon>> {
        return handleState { hackathonRemoteDataSource.searchHackathons(query) }
    }

    override suspend fun getHackathon(id: Int): State<Hackathon> {
        return handleState { hackathonRemoteDataSource.getHackathon(id) }
    }
}