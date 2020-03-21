package com.example.hackathon.data.hackathon.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Hackathon (

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("owner_id")
    @Expose
    val ownerId: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("email")
    @Expose
    val email: String,

    @SerializedName("start_date")
    @Expose
    val startDate: Date,

    @SerializedName("end_date")
    @Expose
    val endDate: Date,

    @SerializedName("address")
    @Expose
    val address: String,

    @SerializedName("thumbnail")
    @Expose
    val thumbnail: String?,

    @SerializedName("header_image")
    @Expose
    val headerImage: String?,

    @SerializedName("full_description")
    @Expose
    val fullDescription: String,

    @SerializedName("website")
    @Expose
    val website: String?,

    @SerializedName("created_at")
    @Expose
    val createdAt: Date,

    @SerializedName("updated_at")
    @Expose
    val updatedAt: Date,

    @SerializedName("thumbnail_url")
    @Expose
    val thumbnailUrl: String,

    @SerializedName("number_of_participants")
    @Expose
    val numberOfParticipants: String
)