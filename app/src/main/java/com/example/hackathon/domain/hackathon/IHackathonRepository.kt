package com.example.hackathon.domain.hackathon

import com.example.hackathon.data.base.model.Paging
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.data.base.model.SuccessResult
import com.example.hackathon.util.state.State

interface IHackathonRepository {

    suspend fun getHackathons(): State<SuccessResult<List<Hackathon>, Paging>>

    suspend fun searchHackathons(query: String): State<SuccessResult<List<Hackathon>, Paging>>

    suspend fun getHackathon(id: Int): State<SuccessResult<Hackathon, Unit>>

    suspend fun checkParticipation(id: Int): State<SuccessResult<Boolean, Unit>>

    suspend fun register(id: Int): State<SuccessResult<Boolean, Unit>>

    suspend fun unregister(id: Int): State<SuccessResult<Boolean, Unit>>
}