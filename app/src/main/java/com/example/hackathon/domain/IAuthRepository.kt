package com.example.hackathon.domain

import com.example.hackathon.base.BaseRemoteDataSource
import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.State

interface IAuthRepository {

    suspend fun signUp(login: String,
                       email: String,
                       password: String): State<User>
}