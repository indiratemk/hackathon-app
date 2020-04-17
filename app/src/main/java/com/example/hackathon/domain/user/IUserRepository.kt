package com.example.hackathon.domain.user

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.state.State

interface IUserRepository {

    suspend fun getUser(): State<Result<User, Unit>>

    suspend fun getPastHackathons(userId: Int): State<Result<List<Hackathon>, Unit>>
}