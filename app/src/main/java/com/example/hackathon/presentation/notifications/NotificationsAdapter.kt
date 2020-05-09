package com.example.hackathon.presentation.notifications

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.presentation.notifications.invitation.InvitationVH
import com.example.hackathon.presentation.notifications.review.ReviewVH

class NotificationsAdapter(private val listener: NotificationClickListener) :
    RecyclerView.Adapter<NotificationVH>() {

    companion object {
        const val INVITATION = 1
        const val REVIEW = 2
    }

    private var notifications: MutableList<Notification> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotificationVH {
        val inflater = LayoutInflater.from(parent.context)
        if (viewType == INVITATION) {
            return InvitationVH(
                inflater.inflate(
                    R.layout.vh_invitation,
                    parent,
                    false
                )
            )
        } else {
            return ReviewVH(
                inflater.inflate(
                    R.layout.vh_review,
                    parent,
                    false
                )
            )
        }
    }

    override fun getItemViewType(position: Int): Int {
        return notifications[position].type
    }

    override fun onBindViewHolder(holder: NotificationVH, position: Int) {
        holder.onBind(notifications[position], listener)
    }

    override fun getItemCount() = notifications.size

    fun isEmpty() = itemCount == 0

    fun setNotifications(notifications: List<Notification>) {
        this.notifications = notifications.toMutableList()
        notifyDataSetChanged()
    }

    fun removeNotification(position: Int) {
        notifications.removeAt(position)
        notifyItemRemoved(position)
    }

    fun updateNotification(position: Int) {
        notifications.get(position).isAccepted = true
        notifyItemChanged(position)
    }
}