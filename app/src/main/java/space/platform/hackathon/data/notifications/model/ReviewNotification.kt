package space.platform.hackathon.data.notifications.model

import space.platform.hackathon.data.hackathon.model.Hackathon
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class ReviewNotification(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("hackathon")
    @Expose
    val hackathon: Hackathon
)