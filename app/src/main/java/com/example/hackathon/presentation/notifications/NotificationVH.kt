package com.example.hackathon.presentation.notifications

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.data.notifications.model.Notification

abstract class NotificationVH(view: View) : RecyclerView.ViewHolder(view) {

    abstract fun onBind(notification: Notification, listener: NotificationClickListener)
}