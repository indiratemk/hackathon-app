package com.example.hackathon.presentation.hackathon.teams

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.afollestad.materialdialogs.MaterialDialog
import com.afollestad.materialdialogs.WhichButton
import com.afollestad.materialdialogs.actions.getActionButton
import com.afollestad.materialdialogs.customview.customView
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Team
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.presentation.hackathon.teams.participants.ParticipantManageClickListener
import com.example.hackathon.presentation.hackathon.teams.participants.ParticipantsManageAdapter
import com.example.hackathon.util.Constants
import com.example.hackathon.util.PreferenceUtils
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_teams.*
import kotlinx.android.synthetic.main.dlg_create_team.*
import kotlinx.android.synthetic.main.dlg_team_management.*
import kotlinx.android.synthetic.main.layout_empty_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class TeamsActivity : BaseActivity(), TeamClickListener, ParticipantManageClickListener {

    companion object {
        fun startActivity(activity: Activity, id: Int?) {
            val intent = Intent(activity, TeamsActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, id)
            activity.startActivity(intent)
        }
    }

    private val teamsViewModel: TeamsViewModel by viewModel()
    private var hackathonId: Int? = null
    private val teamsAdapter = TeamsAdapter(this)


    override fun layoutId() = R.layout.activity_teams

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        teamsViewModel.getTeams(hackathonId!!)
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
            onStateChange(dataState)
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

        teamsViewModel.newTeam.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let {
                hackathonId?.let {
                    teamsViewModel.getTeams(it)
                }
            }
        })

        teamsViewModel.isRemoved.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let { result ->
                if (result.data) {
                    UIUtil.showSuccessMessage(this, getString(R.string.teams_success_team_remove))
                    hackathonId?.let {
                        teamsViewModel.getTeams(it)
                    }
                } else {
                    UIUtil.showErrorMessage(this, getString(R.string.teams_fail_team_remove))
                }
            }
        })

        teamsViewModel.isLeft.observe(this, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let { result ->
                if (result.data) {
                    UIUtil.showSuccessMessage(this, getString(R.string.teams_success_left_team))
                    hackathonId?.let {
                        teamsViewModel.getTeams(it)
                    }
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

    override fun onCreateClick() {
        MaterialDialog(this).show {
            customView(R.layout.dlg_create_team)
            positiveButton(R.string.dlg_team_create)
            positiveButton {
                val team = tietTeam.text.toString().trim()
                if (team.isEmpty()) {
                    tilTeam.error = getString(R.string.dlg_team_empty_field)
                } else {
                    tilTeam.error = null
                    hackathonId?.let {
                        teamsViewModel.createTeam(it, team)
                    }
                    dismiss()
                }
            }
            negativeButton(R.string.dlg_team_cancel)
            negativeButton { dismiss() }
            getActionButton(WhichButton.NEGATIVE).updateTextColor(R.color.colorGray400)
            noAutoDismiss()
        }
    }

    override fun onRemoveTeamClick(teamId: Int) {
        teamsViewModel.removeTeam(teamId)
    }

    override fun onTeamManagementClick(team: Team) {
        val participantsAdapter = ParticipantsManageAdapter(team.members!!,this, team.id)
        MaterialDialog(this).show {
            customView(R.layout.dlg_team_management)
            rvParticipants.apply {
                setHasFixedSize(true)
                layoutManager = LinearLayoutManager(context)
                adapter = participantsAdapter
            }
            negativeButton(R.string.dlg_team_management_close)
            negativeButton { dismiss() }
        }
    }

    override fun onRemoveClick(teamId: Int, userId: Int) {
        teamsViewModel.kickUser(teamId, userId)
    }

    override fun onLeaveTeam(userId: Int) {
        teamsViewModel.leaveTeam(hackathonId!!, userId)
    }
}