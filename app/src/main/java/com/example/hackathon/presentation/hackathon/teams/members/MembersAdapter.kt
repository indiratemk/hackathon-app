package com.example.hackathon.presentation.hackathon.teams.members

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.data.auth.model.User

class MembersAdapter : RecyclerView.Adapter<MemberVH>() {

    private var members: List<User> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MemberVH {
        return MemberVH(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.vh_member, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MemberVH, position: Int) {
        holder.onBind(members[position])
    }

    override fun getItemCount() = members.size

    fun setMembers(members: List<User>) {
        this.members = members
        notifyDataSetChanged()
    }
}