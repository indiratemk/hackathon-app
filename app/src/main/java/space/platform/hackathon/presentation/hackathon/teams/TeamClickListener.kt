package space.platform.hackathon.presentation.hackathon.teams

import space.platform.hackathon.data.hackathon.model.Team

interface TeamClickListener {

    fun onCreateClick()

    fun onRemoveTeamClick(teamId: Int)

    fun onTeamManagementClick(team: Team)

    fun onLeaveTeam(userId: Int)
}