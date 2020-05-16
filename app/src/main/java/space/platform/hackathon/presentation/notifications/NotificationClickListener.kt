package space.platform.hackathon.presentation.notifications

interface NotificationClickListener {

    fun onRemoveClick(id: Int, position: Int)

    fun onAcceptClick(code: String, detailsId: Int, teamId: Int, position: Int)

    fun onGiveFeedBackClick(hackathonId: Int, title: String)
}