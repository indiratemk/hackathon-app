package com.example.hackathon.data.participants.model

import com.example.hackathon.data.auth.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Participant(

    @SerializedName("uid")
    @Expose
    val userId: Int,

    @SerializedName("hid")
    @Expose
    val hackathonId: Int,

    @SerializedName("created_at")
    @Expose
    val createdAt: Date,

    @SerializedName("updated_at")
    @Expose
    val updatedAt: Date,

    @SerializedName("is_confirmed")
    @Expose
    val isConfirmed: Boolean,

    @SerializedName("type")
    @Expose
    val type: Int,

    @SerializedName("team_id")
    @Expose
    val teamId: Int?,

    @SerializedName("team")
    @Expose
    val team: String?,

    @SerializedName("user")
    @Expose
    val user: User
)