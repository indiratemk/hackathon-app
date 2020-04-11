package com.example.hackathon.domain.participants

import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.state.State

interface IParticipantsRepository {

    suspend fun register(id: Int): State<Result<Boolean, Unit>>

    suspend fun unregister(id: Int): State<Result<Boolean, Unit>>

    suspend fun confirmParticipation(hackathonId: Int,
                                     userId: Int): State<Result<Boolean, Unit>>

    suspend fun getParticipatesInHackathons(userId: Int): State<Result<List<Hackathon>, Unit>>
}