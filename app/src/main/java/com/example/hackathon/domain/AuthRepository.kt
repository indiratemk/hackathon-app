package com.example.hackathon.domain

import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.ApiResponse
import com.example.hackathon.util.State
import com.example.hackathon.util.exception.ApiException
import com.example.hackathon.util.exception.NetworkException

open class AuthRepository(private val authRemoteDataSource: AuthRemoteDataSource) :
    IAuthRepository {

    override suspend fun signUp(login: String, email: String, password: String): State<User> {
        return when (val response = authRemoteDataSource.signUp(login, email, password)) {
            is ApiResponse.Success ->
                State.Success(response.data)
            is ApiResponse.Error -> {
                when (response.exception) {
                    is ApiException -> State.BackendError(response.exception.errorCode, response.exception.errorMessage)
                    is NetworkException -> State.NetworkError(response.exception.errorMessage())
                    else -> State.NetworkError(NetworkException().errorMessage())
                }
            }

        }
    }
}