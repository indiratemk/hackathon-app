package com.example.hackathon.base

import com.example.hackathon.util.ApiResponse
import com.example.hackathon.util.state.State
import com.example.hackathon.util.exception.ApiException
import com.example.hackathon.util.exception.NetworkException

open class BaseRepository {

    suspend fun <T: Any> handleState(call: suspend() -> ApiResponse<T>): State<T> {
        return when (val apiResponse = call()) {
            is ApiResponse.Success ->
                State.Success(apiResponse.data)
            is ApiResponse.Error -> {
                when (apiResponse.exception) {
                    is ApiException -> State.BackendError(apiResponse.exception.errorCode,
                        apiResponse.exception.errorMessage)
                    is NetworkException -> State.NetworkError(apiResponse.exception.errorMessage())
                    else -> State.NetworkError(NetworkException().errorMessage())
                }
            }

        }
    }
}