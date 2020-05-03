package com.example.hackathon.presentation.hackathon.teams

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.PreferenceUtils
import kotlinx.android.synthetic.main.activity_teams.*
import kotlinx.android.synthetic.main.layout_empty_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeamsActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, id: Int?) {
            val intent = Intent(activity, TeamsActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, id)
            activity.startActivity(intent)
        }
    }

    private val teamsViewModel: TeamsViewModel by viewModel()
    private var hackathonId: Int? = null
    private val teamsAdapter = TeamsAdapter()


    override fun layoutId() = R.layout.activity_teams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        teamsViewModel.getTeam(hackathonId!!)
        initToolbar(toolbar, getString(R.string.teams_title), false)
        rvTeams.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = teamsAdapter
        }
    }

    private fun subscribeObservers() {
        teamsViewModel.current.observe(this, Observer { dataState ->
            dataState.result?.let { result -> teamsAdapter.setCurrentUser(result.data) }
        })

        teamsViewModel.teams.observe(this, Observer { dataState ->
            dataState.isLoading.let { isLoading ->
                if (isLoading) {
                    showLoading()
                } else {
                    hideLoading()
                }
            }

            dataState.result?.let { result ->
                if (result.data.isEmpty() && !PreferenceUtils.isAuthorized(this)) {
                    llEmptyList.visibility = View.VISIBLE
                    tvEmptyListMessage.text = getString(R.string.teams_empty_list)
                    rvTeams.visibility = View.GONE
                } else {
                    teamsAdapter.setTeams(result.data)
                }
            }
        })
    }

    private fun showLoading() {
        progressBar.visibility = View.VISIBLE
        rvTeams.visibility = View.GONE
    }

    private fun hideLoading() {
        progressBar.visibility = View.GONE
        rvTeams.visibility = View.VISIBLE
    }
}