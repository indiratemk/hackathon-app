package space.platform.hackathon.presentation.tasks

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_tasks.*
import kotlinx.android.synthetic.main.layout_empty_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel
import space.platform.hackathon.R
import space.platform.hackathon.data.tasks.Task
import space.platform.hackathon.presentation.base.BaseActivity
import space.platform.hackathon.util.Constants

class TasksActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, id: Int?) {
            val intent = Intent(activity, TasksActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, id)
            activity.startActivity(intent)
        }
    }

    private val tasksViewModel: TasksViewModel by viewModel()
    private var hackathonId: Int? = null

    override fun layoutId() = R.layout.activity_tasks

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        subscribeObservers()
        initUI()
    }

    private fun subscribeObservers() {
        tasksViewModel.tasks.observe(this, Observer { dataState ->
            onStateChange(dataState)

            dataState.isLoading.let { isLoading ->
                if (isLoading) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }

            dataState.result?.let { result ->
                if (result.data.isEmpty()) {
                    llEmptyList.visibility = View.VISIBLE
                    tvEmptyListMessage.text = getString(R.string.tasks_empty_list)
                    rvTasks.visibility = View.GONE
                } else {
                    val modifiedTasks = mutableListOf<Task>()
                    for (task in result.data) {
                        task.taskDescs = listOf(task.description).toMutableList()
                        modifiedTasks.add(task)
                    }
                    val expandableAdapter = TaskExpandableAdapter(this, modifiedTasks)
                    expandableAdapter.setCustomParentAnimationViewId(R.id.ivArrow)
                    expandableAdapter.setParentClickableViewAnimationDefaultDuration()
                    expandableAdapter.setParentAndIconExpandOnClick(true)
                    rvTasks.apply {
                        setHasFixedSize(true)
                        layoutManager = LinearLayoutManager(context)
                        adapter = expandableAdapter
                    }
                }
            }
        })
    }

    private fun initUI() {
        initToolbar(toolbar, getString(R.string.tasks_title), false)
        tasksViewModel.getTasks(hackathonId!!)
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        rvTasks.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        rvTasks.visibility = View.VISIBLE
    }
}