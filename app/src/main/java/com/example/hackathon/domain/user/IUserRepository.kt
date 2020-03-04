package com.example.hackathon.domain.user

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.state.State

interface IUserRepository {
    suspend fun getUser(): State<User>
}