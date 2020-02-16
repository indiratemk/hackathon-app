package com.example.hackathon.util.error

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error (

    @SerializedName("statusCode")
    @Expose
    val statusCode: Int,

    @SerializedName("message")
    @Expose
    val message: String
)