package space.platform.hackathon.presentation.hackathon.teams

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import space.platform.hackathon.data.participants.model.Participant
import kotlinx.android.synthetic.main.vh_teams_header.view.*

class TeamsHeaderVH(view: View) : RecyclerView.ViewHolder(view) {

    private var btnCreateTeam = view.btnCreateTeam
    private var llTeamManagement = view.llTeamManagement
    private var tvTeamTitle = view.tvTeamTitle
    private var llManagementActions = view.llManagementActions
    private var btnLeaveTeam = view.btnLeaveTeam
    private var btnRemoveTeam = view.btnRemoveTeam
    private var btnParticipantsManagement = view.btnParticipantsManagement

    fun onBind(currentUser: Participant?, listener: TeamClickListener) {
        currentUser?.let {
            when {
                currentUser.teamId == null -> {
                    btnCreateTeam.setOnClickListener { listener.onCreateClick() }
                    showCreateButton()
                }
                currentUser.userId != currentUser.team?.ownerId -> {
                    btnLeaveTeam.setOnClickListener { listener.onLeaveTeam(currentUser.userId) }
                    showLeaveButton(currentUser)
                }
                else -> {
                    btnRemoveTeam.setOnClickListener { listener.onRemoveTeamClick(currentUser.teamId) }
                    btnParticipantsManagement.setOnClickListener { listener.onTeamManagementClick(currentUser.team) }
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