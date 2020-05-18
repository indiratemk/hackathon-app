package space.platform.hackathon.presentation.tasks

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter
import space.platform.hackathon.R
import space.platform.hackathon.data.tasks.Task

class TaskExpandableAdapter(private val context: Context,
                            tasks: List<Task>) :
    ExpandableRecyclerAdapter<TaskParentVH, TaskChildVH>(context, tasks) {

    override fun onCreateParentViewHolder(viewGroup: ViewGroup?): TaskParentVH {
        return TaskParentVH(LayoutInflater.from(context)
            .inflate(R.layout.vh_task_title, viewGroup, false))
    }

    override fun onCreateChildViewHolder(viewGroup: ViewGroup?): TaskChildVH {
        return TaskChildVH(LayoutInflater.from(context)
            .inflate(R.layout.vh_task_desc, viewGroup, false))
    }

    override fun onBindParentViewHolder(parentHolder: TaskParentVH?, i: Int, parentObject: Any?) {
        val task = parentObject as Task
        parentHolder?.tvTaskTitle?.text = task.title
    }

    override fun onBindChildViewHolder(childHolder: TaskChildVH?, i: Int, childObject: Any?) {
        val desc = childObject as String
        childHolder?.tvTaskDescription?.text = desc
    }
}