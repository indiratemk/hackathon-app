package com.example.hackathon.data.hackathon.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class QRParams(

    @SerializedName("hid")
    @Expose
    val hackathonId: Int,

    @SerializedName("uid")
    @Expose
    val userId: Int
)