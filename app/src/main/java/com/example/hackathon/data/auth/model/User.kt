package com.example.hackathon.data.auth.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class User(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("login")
    @Expose
    val login: String
)