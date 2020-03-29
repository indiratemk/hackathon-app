package com.example.hackathon.domain.hackathon

import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.util.state.State

interface IHackathonRepository {

    suspend fun getHackathons(): State<Result<List<Hackathon>, Paging>>

    suspend fun searchHackathons(query: String): State<Result<List<Hackathon>, Paging>>

    suspend fun getHackathon(id: Int): State<Result<Hackathon, Unit>>

    suspend fun checkParticipation(id: Int): State<Result<Boolean, Unit>>
}