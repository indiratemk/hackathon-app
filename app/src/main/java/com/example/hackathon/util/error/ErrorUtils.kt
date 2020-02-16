package com.example.hackathon.util.error

import com.google.gson.Gson
import retrofit2.Response

class ErrorUtils {

    companion object {
        fun parseError(response: Response<*>) =
            Gson().fromJson(response.errorBody()?.string(), ApiError::class.java).error
    }
}