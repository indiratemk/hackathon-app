package com.example.hackathon.data.base.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class SuccessResult<D, M>(

    @SerializedName("meta")
    @Expose
    val meta: M,

    @SerializedName("data")
    @Expose
    val data: D,

    @SerializedName("links")
    @Expose
    val links: Links
)