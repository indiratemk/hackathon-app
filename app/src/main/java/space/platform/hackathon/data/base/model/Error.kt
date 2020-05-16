package space.platform.hackathon.data.base.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Error (

    @SerializedName("statusCode")
    @Expose
    val statusCode: Int,

    @SerializedName("message")
    @Expose
    val message: String
)