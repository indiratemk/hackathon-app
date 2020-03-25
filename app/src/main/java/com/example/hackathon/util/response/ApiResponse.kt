package com.example.hackathon.util.response

import java.lang.Exception

sealed class ApiResponse<T> {
    data class Success<T>(val data: T): ApiResponse<T>()
    data class Error<T>(val exception: Exception): ApiResponse<T>()
}