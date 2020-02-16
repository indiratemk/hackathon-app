package com.example.hackathon.util.error

import com.example.hackathon.util.error.Error
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ApiError (

    @SerializedName("error")
    @Expose
    val error: Error
)