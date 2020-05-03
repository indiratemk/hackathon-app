package com.example.hackathon.presentation.hackathon.teams

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Team
import com.example.hackathon.data.participants.model.Participant

class TeamsAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    companion object {
        const val HEADER_TYPE = 0
        const val TEAM_TYPE = 1
    }

    private var teams: List<Team> = emptyList()
    private var currentUser: Participant? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == HEADER_TYPE) {
            return TeamsHeaderVH(LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_teams_header, parent, false))
        } else {
            return TeamVH(LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_team, parent, false))
        }
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0 && currentUser != null) {
            return HEADER_TYPE
        } else {
            return TEAM_TYPE
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (currentUser != null) {
            when (holder) {
                is TeamsHeaderVH -> holder.onBind(currentUser)
                is TeamVH -> holder.onBind(teams[position - 1])
            }
        } else {
            (holder as TeamVH).onBind(teams[position])
        }
    }

    override fun getItemCount(): Int {
        if (currentUser != null) return teams.size + 1 else return teams.size
    }

    fun setCurrentUser(currentUser: Participant?) {
        this.currentUser = currentUser
    }

    fun setTeams(teams: List<Team>) {
        this.teams = teams
        notifyDataSetChanged()
    }
}