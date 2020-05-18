package space.platform.hackathon.presentation.tasks

import android.view.View
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder
import kotlinx.android.synthetic.main.vh_task_title.view.*

class TaskParentVH(view: View) : ParentViewHolder(view) {

    var tvTaskTitle = view.tvTaskTitle
}