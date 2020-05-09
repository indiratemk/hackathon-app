package com.example.hackathon.presentation.notifications.review

import android.view.View
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.data.notifications.model.ReviewNotification
import com.example.hackathon.presentation.notifications.NotificationClickListener
import com.example.hackathon.presentation.notifications.NotificationVH
import com.google.gson.Gson
import kotlinx.android.synthetic.main.vh_review.view.*

class ReviewVH(view: View) : NotificationVH(view) {

    private var tvTitle = view.tvTitle

    override fun onBind(notification: Notification, listener: NotificationClickListener) {
        val review =
            Gson().fromJson(notification.details, ReviewNotification::class.java)
        tvTitle.text = review.hackathon.title
    }
}