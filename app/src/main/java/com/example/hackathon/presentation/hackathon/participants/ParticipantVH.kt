package com.example.hackathon.presentation.hackathon.participants

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.participants.model.Participant
import com.example.hackathon.util.Constants
import kotlinx.android.synthetic.main.vh_participant.view.*

class ParticipantVH(view: View) : RecyclerView.ViewHolder(view) {

    private val civAvatar = view.civAvatar
    private val tvFullName = view.tvFullName
    private val tvLogin = view.tvLogin
    private val tvIdentification = view.tvIdentification
    private val btnInvite = view.btnInvite

    fun onBind(participant: Participant, currentUser: Participant?, listener: ParticipantListener) {
        Glide.with(itemView.context)
            .load(participant.user.avatarUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(civAvatar)
        if (participant.user.name == null && participant.user.surname == null) {
            tvFullName.text = itemView.resources.getString(R.string.participants_name_undefined)
        } else {
            tvFullName.text = itemView.resources.getString(
                R.string.participants_full_name,
                participant.user.name,
                participant.user.surname
            )
        }
        tvLogin.text = itemView.resources.getString(R.string.participants_login, participant.user.login)

        currentUser?.let {
            tvIdentification.visibility = if (it.userId == participant.userId) View.VISIBLE else View.GONE
            btnInvite.visibility = if (canInvite(participant, it)) View.VISIBLE else View.GONE
        }

        itemView.setOnClickListener { listener.onParticipantClick(participant.user.email) }
    }

    private fun canInvite(participant: Participant, currentUser: Participant) = currentUser.teamId != null &&
            participant.teamId == null &&
            currentUser.type == Constants.WITH_TEAM &&
            participant.type == Constants.SEARCHING_FOR_TEAM
}