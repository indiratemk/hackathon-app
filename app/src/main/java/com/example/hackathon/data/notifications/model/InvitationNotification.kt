package com.example.hackathon.data.notifications.model

import com.example.hackathon.data.auth.model.User
import com.example.hackathon.data.hackathon.model.Team
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class InvitationNotification(

    @SerializedName("code")
    @Expose
    val code: String,

    @SerializedName("status")
    @Expose
    val status: String,

    @SerializedName("team")
    @Expose
    val team: Team,

    @SerializedName("user")
    @Expose
    val user: User
)