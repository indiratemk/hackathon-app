package com.example.hackathon.data.base

import com.example.hackathon.util.response.ApiResponse
import com.example.hackathon.util.response.error.ErrorUtils
import com.example.hackathon.util.exception.ApiException
import com.example.hackathon.util.exception.NetworkException
import retrofit2.Response

open class BaseRemoteDataSource {

    suspend fun <T: Any> getResponse(call: suspend() -> Response<T>): ApiResponse<T> {
        try {
            val response = call()
            if (response.isSuccessful) {
                return ApiResponse.Success(response.body()!!)
            }
            val error = ErrorUtils.parseError(response)
            return ApiResponse.Error(ApiException(error.statusCode, error.message))
        } catch (e: Exception) {
            e.printStackTrace()
            return ApiResponse.Error(NetworkException())
        }
    }
}