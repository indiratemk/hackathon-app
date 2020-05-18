package space.platform.hackathon.presentation.tasks

import android.view.View
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder
import kotlinx.android.synthetic.main.vh_task_desc.view.*

class TaskChildVH(view: View) : ChildViewHolder(view) {

    var tvTaskDescription = view.tvTaskDescription
}