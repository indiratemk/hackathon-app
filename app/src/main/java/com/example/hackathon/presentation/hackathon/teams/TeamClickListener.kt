package com.example.hackathon.presentation.hackathon.teams

import com.example.hackathon.data.hackathon.model.Team

interface TeamClickListener {

    fun onCreateClick()

    fun onRemoveTeamClick(teamId: Int)

    fun onTeamManagementClick(team: Team)
}