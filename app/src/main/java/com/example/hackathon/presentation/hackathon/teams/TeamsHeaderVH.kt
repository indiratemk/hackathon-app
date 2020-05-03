package com.example.hackathon.presentation.hackathon.teams

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.data.participants.model.Participant
import com.example.hackathon.util.Constants
import kotlinx.android.synthetic.main.vh_teams_header.view.*

class TeamsHeaderVH(view: View) : RecyclerView.ViewHolder(view) {

    private var btnCreateTeam = view.btnCreateTeam
    private var llTeamManagement = view.llTeamManagement
    private var tvTeamTitle = view.tvTeamTitle
    private var llManagementActions = view.llManagementActions
    private var btnLeaveTeam = view.btnLeaveTeam

    fun onBind(currentUser: Participant?) {
        currentUser?.let {
            when {
                currentUser.teamId == null -> {
                    showCreateButton()
                }
                currentUser.type == Constants.SEARCHING_FOR_TEAM -> {
                    showLeaveButton(currentUser)
                }
                else -> {
                    showManagementActions(currentUser)
                }
            }
        }
    }

    private fun showCreateButton() {
        btnCreateTeam.visibility = View.VISIBLE
        llTeamManagement.visibility = View.GONE
    }

    private fun showLeaveButton(currentUser: Participant) {
        btnCreateTeam.visibility = View.GONE
        llTeamManagement.visibility = View.VISIBLE
        currentUser.team?.let {
            tvTeamTitle.text = it.name
        }
        llManagementActions.visibility = View.GONE
        btnLeaveTeam.visibility = View.VISIBLE
    }

    private fun showManagementActions(currentUser: Participant) {
        btnCreateTeam.visibility = View.GONE
        llTeamManagement.visibility = View.VISIBLE
        currentUser.team?.let {
            tvTeamTitle.text = it.name
        }
        llManagementActions.visibility = View.VISIBLE
        btnLeaveTeam.visibility = View.GONE
    }
}