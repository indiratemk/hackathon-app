package com.example.hackathon.presentation.hackathon.participants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.data.auth.model.User

class ParticipantsAdapter: RecyclerView.Adapter<ParticipantVH>() {

    private var participants: List<User> = emptyList()
    private var currentUser: User? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_participant, parent, false)
        return ParticipantVH(view)
    }

    override fun onBindViewHolder(holder: ParticipantVH, position: Int) {
        holder.onBind(participants.get(position), currentUser)
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    fun setParticipants(participants: List<User>) {
        this.participants = participants
        notifyDataSetChanged()
    }

    fun setCurrentUser(currentUser: User?) {
        this.currentUser = currentUser
    }
}