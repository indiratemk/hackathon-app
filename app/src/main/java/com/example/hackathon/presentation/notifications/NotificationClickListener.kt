package com.example.hackathon.presentation.notifications

interface NotificationClickListener {

    fun onRemoveClick(id: Int, position: Int)

    fun onAcceptClick(code: String, detailsId: Int, teamId: Int, position: Int)
}