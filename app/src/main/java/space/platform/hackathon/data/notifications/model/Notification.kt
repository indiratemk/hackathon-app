package space.platform.hackathon.data.notifications.model

import com.google.gson.JsonObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.util.*

data class Notification(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("details_id")
    @Expose
    val detailsId: Int,

    @SerializedName("details")
    @Expose
    val details: JsonObject,

    @SerializedName("is_new")
    @Expose
    val isNew: Boolean,

    @SerializedName("is_removed")
    @Expose
    val isRemoved: Boolean,

    @SerializedName("receiver_id")
    @Expose
    val receiverId: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("type")
    @Expose
    val type: Int,

    @SerializedName("type_code")
    @Expose
    val typeCode: String,

    @SerializedName("created_at")
    @Expose
    val createdAt: Date,

    @SerializedName("updated_at")
    @Expose
    val updatedAt: Date,

    var isAccepted: Boolean
)