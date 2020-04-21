package com.example.hackathon.presentation.hackathon.participants

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.util.Constants
import kotlinx.android.synthetic.main.activity_participants.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantsActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, id: Int?) {
            val intent = Intent(activity, ParticipantsActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, id)
            activity.startActivity(intent)
        }
    }

    private var hackathonId: Int? = null
    private val participantsViewModel: ParticipantsViewModel by viewModel()
    private val participantsAdapter = ParticipantsAdapter()

    override fun layoutId() = R.layout.activity_participants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        participantsViewModel.getParticipants(hackathonId!!)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        initToolbar(toolbar, "Участники", false)
        initRV()
    }

    private fun initRV() {
        rvParticipants.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = participantsAdapter
        }
    }

    private fun subscribeObservers() {
        participantsViewModel.participants.observe(this, Observer { dataState ->
            dataState.result?.let { result ->
                participantsAdapter.setParticipants(result.data)
            }
        })

        participantsViewModel.currentUser.observe(this, Observer { user ->
            participantsAdapter.setCurrentUser(user)
        })
    }
}