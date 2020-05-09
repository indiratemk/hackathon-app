package com.example.hackathon.presentation.notifications.review

import android.text.format.DateUtils
import android.view.View
import com.example.hackathon.data.notifications.model.Notification
import com.example.hackathon.data.notifications.model.ReviewNotification
import com.example.hackathon.presentation.notifications.NotificationClickListener
import com.example.hackathon.presentation.notifications.NotificationVH
import com.google.gson.Gson
import kotlinx.android.synthetic.main.vh_review.view.*

class ReviewVH(view: View) : NotificationVH(view) {

    private var tvDate = view.tvDate
    private var tvRemove = view.tvRemove
    private var tvTitle = view.tvTitle
    private var btnGiveFeedback = view.btnGiveFeedback

    override fun onBind(notification: Notification, listener: NotificationClickListener) {
        val review =
            Gson().fromJson(notification.details, ReviewNotification::class.java)
        tvDate.text = DateUtils.getRelativeDateTimeString(itemView.context,
            notification.createdAt.time, DateUtils.MINUTE_IN_MILLIS,
            DateUtils.WEEK_IN_MILLIS, 0).split(",")[0]
        tvTitle.text = review.hackathon.title
        tvRemove.setOnClickListener {
            listener.onRemoveClick(notification.id, adapterPosition)
        }
        btnGiveFeedback.setOnClickListener { listener.onGiveFeedBackClick(review.hackathon.id, review.hackathon.title) }
    }
}