package space.platform.hackathon.presentation.hackathon.teams.participants

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.platform.hackathon.R
import space.platform.hackathon.data.auth.model.User

class ParticipantsManageAdapter(private val users: List<User>,
                                private val listener: ParticipantManageClickListener,
                                private val teamId: Int) :
    RecyclerView.Adapter<ParticipantManageVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParticipantManageVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_participant_manage, parent, false)
        return ParticipantManageVH(view)
    }

    override fun onBindViewHolder(holder: ParticipantManageVH, position: Int) {
        holder.onBind(users[position], teamId, listener)
    }

    override fun getItemCount() = users.size
}