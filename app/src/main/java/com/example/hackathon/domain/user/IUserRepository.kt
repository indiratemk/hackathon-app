package com.example.hackathon.domain.user

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.base.model.Result
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.state.State

interface IUserRepository {

    suspend fun getCurrentUser(): State<Result<User, Unit>>

    suspend fun getUserByEmail(email: String): State<Result<User, Unit>>

    suspend fun getOwnHackathons(userId: Int): State<Result<List<Hackathon>, Unit>>

    fun getCurrentUserId(): Int
}