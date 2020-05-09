package com.example.hackathon.presentation.notifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_notifications.*
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationsActivity : BaseActivity(),
    NotificationClickListener {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, NotificationsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val notificationsViewModel: NotificationsViewModel by viewModel()
    private val notificationsAdapter = NotificationsAdapter(this)
    private var positionForUpdate: Int = -1

    override fun layoutId() = R.layout.activity_notifications

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun subscribeObservers() {
        notificationsViewModel.notifications.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let { result ->
                notificationsAdapter.setNotifications(result.data)
            }
        })

        notificationsViewModel.isRemoved.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let {
                notificationsAdapter.removeNotification(positionForUpdate)
            }
        })

        notificationsViewModel.isAccepted.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let {
                notificationsAdapter.updateNotification(positionForUpdate)
                UIUtil.showSuccessMessage(this, getString(R.string.notifications_invitation_accepted))
            }
        })
    }

    private fun initUI() {
        initRV()
    }

    private fun initRV() {
        rvNotifications.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = notificationsAdapter
        }
    }

    override fun onRemoveClick(id: Int, position: Int) {
        positionForUpdate = position
        notificationsViewModel.removeNotification(id)
    }

    override fun onAcceptClick(code: String, detailsId: Int, teamId: Int, position: Int) {
        positionForUpdate = position
        notificationsViewModel.acceptInvite(code, detailsId, teamId)
    }
}
