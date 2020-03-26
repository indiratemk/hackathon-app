package com.example.hackathon.data.hackathon.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Tag(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("title")
    @Expose
    val title: String
)