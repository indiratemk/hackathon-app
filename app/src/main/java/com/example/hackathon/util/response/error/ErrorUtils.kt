package com.example.hackathon.util.response.error

import com.example.hackathon.data.base.model.Error
import com.google.gson.Gson
import retrofit2.Response

class ErrorUtils {

    companion object {
        fun parseError(response: Response<*>) =
            Gson().fromJson(response.errorBody()?.string(), Error::class.java)
    }
}