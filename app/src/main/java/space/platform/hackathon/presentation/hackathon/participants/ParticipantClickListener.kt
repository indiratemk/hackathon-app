package space.platform.hackathon.presentation.hackathon.participants

interface ParticipantClickListener {

    fun onParticipantClick(email: String)

    fun onInviteClick(receiverId: Int, teamId: Int)
}