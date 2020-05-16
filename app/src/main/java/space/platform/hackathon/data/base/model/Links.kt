package space.platform.hackathon.data.base.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Links(

    @SerializedName("prev")
    @Expose
    val prev: String?,

    @SerializedName("next")
    @Expose
    val next: String?
)