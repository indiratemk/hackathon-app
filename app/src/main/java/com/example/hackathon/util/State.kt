package com.example.hackathon.util

sealed class State<T>(
    val data: T? = null,
    val message: String? = null,
    val errorCode: Int? = 200
) {
    class Success<T>(data: T) : State<T>(data)
    class Loading<T>() : State<T>()
    class NetworkError<T>(message: String) : State<T>(message = message)
    class BackendError<T>(errorCode: Int, message: String) : State<T>(errorCode = errorCode, message = message)
}