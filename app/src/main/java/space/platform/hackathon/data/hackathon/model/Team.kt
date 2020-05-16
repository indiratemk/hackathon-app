package space.platform.hackathon.data.hackathon.model

import space.platform.hackathon.data.auth.model.User
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Team(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("hid")
    @Expose
    val hackathonId: Int,

    @SerializedName("members")
    @Expose
    val members: List<User>?,

    @SerializedName("name")
    @Expose
    val name: String,

    @SerializedName("owner_id")
    @Expose
    val ownerId: Int
)