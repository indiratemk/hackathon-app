package com.example.hackathon.presentation.hackathon.teams.members

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.auth.model.User
import kotlinx.android.synthetic.main.vh_member.view.*

class MemberVH(view: View) : RecyclerView.ViewHolder(view) {

    private var ivMember = view.ivMember

    fun onBind(member: User) {
        Glide.with(itemView.context)
            .load(member.avatarUrl)
            .placeholder(R.drawable.no_avatar)
            .error(R.drawable.no_avatar)
            .into(ivMember)
    }
}