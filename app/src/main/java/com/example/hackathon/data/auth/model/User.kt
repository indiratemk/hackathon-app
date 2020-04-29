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
    val login: String,

    @SerializedName("name")
    @Expose
    val name: String?,

    @SerializedName("surname")
    @Expose
    val surname: String?,

    @SerializedName("phone_number")
    @Expose
    val phoneNumber: String?,

    @SerializedName("phone_code")
    @Expose
    val phoneCode: String?,

    @SerializedName("gender")
    @Expose
    val gender: String?,

    @SerializedName("github_profile")
    @Expose
    val githubProfile: String?,

    @SerializedName("linkedin_profile")
    @Expose
    val linkedinProfile: String?,

    @SerializedName("bio")
    @Expose
    val bio: String?,

    @SerializedName("avatar")
    @Expose
    val avatar: String?,

    @SerializedName("avatar_url")
    @Expose
    val avatarUrl: String?
)