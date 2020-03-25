package com.example.hackathon.data.base.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Paging(

    @SerializedName("total")
    @Expose
    val total: Int,

    @SerializedName("current")
    @Expose
    val current: Int,

    @SerializedName("size")
    @Expose
    val size: Int
)