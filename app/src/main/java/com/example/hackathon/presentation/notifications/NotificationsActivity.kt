package com.example.hackathon.presentation.notifications

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import org.koin.android.viewmodel.ext.android.viewModel

class NotificationsActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, NotificationsActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val notificationsViewModel: NotificationsViewModel by viewModel()

    override fun layoutId() = R.layout.activity_notifications

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {}

    private fun subscribeObservers() {
        notificationsViewModel.notifications.observe(this, Observer { dataState ->
            Log.d("taaaaaaag", "result: $dataState")
        })
    }
}
