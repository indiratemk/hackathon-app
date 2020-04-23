package com.example.hackathon.presentation.hackathon.participants

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.util.Constants
import kotlinx.android.synthetic.main.vh_participant.view.*

class ParticipantVH(view: View): RecyclerView.ViewHolder(view) {

    private val civAvatar = view.civAvatar
    private val tvFullName = view.tvFullName
    private val tvLogin = view.tvLogin
    private val tvIdentification = view.tvIdentification
    private val btnInvite = view.btnInvite

    fun onBind(participant: User, currentUser: User?, listener: ParticipantListener) {
        Glide.with(itemView.context)
            .load(participant.avatarUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(civAvatar)
        if (participant.name == null && participant.surname == null) {
            tvFullName.text = itemView.resources.getString(R.string.participants_name_undefined)
        } else {
            tvFullName.text = itemView.resources.getString(
                R.string.participants_full_name,
                participant.name,
                participant.surname
            )
        }
        tvLogin.text = itemView.resources.getString(R.string.participants_login, participant.login)

        currentUser?.let {
            tvIdentification.visibility = if (it.id == participant.id) View.VISIBLE else View.GONE
            btnInvite.visibility = if (canInvite(participant, it)) View.VISIBLE else View.GONE
        }

        itemView.setOnClickListener { listener.onParticipantClick(participant.email) }
    }

    private fun canInvite(participant: User, currentUser: User) = currentUser.teamId != null &&
            participant.teamId == null &&
            currentUser.participantType == Constants.WITH_TEAM &&
            participant.participantType == Constants.SEARCHING_FOR_TEAM
}