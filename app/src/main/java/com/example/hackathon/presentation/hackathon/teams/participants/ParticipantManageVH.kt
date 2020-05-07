package com.example.hackathon.presentation.hackathon.teams.participants

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.auth.model.User
import kotlinx.android.synthetic.main.vh_participant_manage.view.*

class ParticipantManageVH(view: View) : RecyclerView.ViewHolder(view) {

    private val civAvatar = view.civAvatar
    private val tvFullName = view.tvFullName
    private val tvLogin = view.tvLogin
    private val btnRemove = view.btnRemoveParticipant

    fun onBind(user: User, teamId: Int, listener: ParticipantManageClickListener) {
        Glide.with(itemView.context)
            .load(user.avatarUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(civAvatar)
        if (user.name == null && user.surname == null) {
            tvFullName.text = itemView.resources.getString(R.string.participants_name_undefined)
        } else {
            tvFullName.text = itemView.resources.getString(
                R.string.participants_full_name,
                user.name,
                user.surname
            )
        }
        tvLogin.text = itemView.resources.getString(R.string.participants_login, user.login)
        btnRemove.setOnClickListener { listener.onRemoveClick(teamId, user.id) }
    }
}