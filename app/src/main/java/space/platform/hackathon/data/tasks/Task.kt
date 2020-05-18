package space.platform.hackathon.data.tasks

import com.bignerdranch.expandablerecyclerview.Model.ParentObject
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Task(

    @SerializedName("id")
    @Expose
    val id: Int,

    @SerializedName("hid")
    @Expose
    val hackathonId: Int,

    @SerializedName("author_id")
    @Expose
    val authorId: Int,

    @SerializedName("title")
    @Expose
    val title: String,

    @SerializedName("description")
    @Expose
    val description: String
) : ParentObject {

    var taskDescs = mutableListOf<Any>()

    override fun getChildObjectList(): MutableList<Any> {
        return taskDescs
    }

    override fun setChildObjectList(taskDescs: MutableList<Any>?) {
        this.taskDescs = taskDescs!!
    }
}