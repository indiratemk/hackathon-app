package com.example.hackathon.presentation.notifications.invitation

import android.text.format.DateUtils
import android.view.View
import androidx.core.text.HtmlCompat
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.notifications.model.InvitationNotification
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.presentation.notifications.NotificationClickListener
import com.example.hackathon.presentation.notifications.NotificationVH
import com.example.hackathon.util.Constants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.vh_invitation.view.*

class InvitationVH(view: View) : NotificationVH(view) {

    private var tvDate = view.tvDate
    private var tvRemove = view.tvRemove
    private var civAvatar = view.civAvatar
    private var tvLogin = view.tvLogin
    private var btnAccept = view.btnAccept

    override fun onBind(notification: Notification, listener: NotificationClickListener) {
        val invitation =
            Gson().fromJson(notification.details, InvitationNotification::class.java)
        Glide.with(itemView.context)
            .load(invitation.user.avatarUrl)
            .placeholder(R.drawable.no_avatar)
            .error(R.drawable.no_avatar)
            .into(civAvatar)
        tvLogin.text = HtmlCompat.fromHtml(itemView.context.getString(R.string.notifications_invitation_message,
            invitation.user.login), HtmlCompat.FROM_HTML_MODE_LEGACY)
        tvDate.text = DateUtils.getRelativeDateTimeString(itemView.context,
            notification.createdAt.time, DateUtils.MINUTE_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS, 0).split(",")[0]
        tvRemove.setOnClickListener {
            listener.onRemoveClick(notification.id, adapterPosition)
        }
        btnAccept.visibility = if (invitation.status.equals(Constants.ACCEPTED) ||
            notification.isAccepted) View.GONE else View.VISIBLE
        btnAccept.setOnClickListener {
            listener.onAcceptClick(invitation.code,
                notification.detailsId, invitation.team.id, adapterPosition)
        }
    }
}