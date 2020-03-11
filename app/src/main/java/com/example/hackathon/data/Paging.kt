package com.example.hackathon.data

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Paging<T>(

    @SerializedName("total")
    @Expose
    val total: Int,

    @SerializedName("results")
    @Expose
    val results: List<T>,

    @SerializedName("prevPageUrl")
    @Expose
    val prevPageUrl: String?,

    @SerializedName("nextPageUrl")
    @Expose
    val nextPageUrl: String?,

    @SerializedName("current")
    @Expose
    val current: Int,

    @SerializedName("size")
    @Expose
    val size: Int
)