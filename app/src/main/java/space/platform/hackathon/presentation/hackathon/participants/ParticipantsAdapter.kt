package space.platform.hackathon.presentation.hackathon.participants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.platform.hackathon.R
import space.platform.hackathon.data.participants.model.Participant

class ParticipantsAdapter(private val listener: ParticipantClickListener) :
    RecyclerView.Adapter<ParticipantVH>() {

    private var participants: List<Participant> = emptyList()
    private var currentUser: Participant? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_participant, parent, false)
        return ParticipantVH(view)
    }

    override fun onBindViewHolder(holder: ParticipantVH, position: Int) {
        holder.onBind(participants.get(position), currentUser, listener)
    }

    override fun getItemCount(): Int {
        return participants.size
    }

    fun setParticipants(participants: List<Participant>) {
        this.participants = participants
        notifyDataSetChanged()
    }

    fun setCurrentUser(currentUser: Participant?) {
        this.currentUser = currentUser
    }
}