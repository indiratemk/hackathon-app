package space.platform.hackathon.presentation.profile.selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.platform.hackathon.R
import space.platform.hackathon.data.hackathon.model.Hackathon

class HackathonSelectionAdapter : RecyclerView.Adapter<HackathonSelectionVH>() {

    private var hackathons: List<Hackathon> = emptyList()
    private var listener: HackathonSelectionClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackathonSelectionVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_hackathon_selection, parent, false)
        return HackathonSelectionVH(view)
    }

    override fun onBindViewHolder(holder: HackathonSelectionVH, position: Int) {
        holder.onBind(hackathons.get(position), listener)
    }

    override fun getItemCount() = hackathons.size

    fun setHackathons(hackathons: List<Hackathon>) {
        this.hackathons = hackathons
        notifyDataSetChanged()
    }

    fun setHackathonListener(listener: HackathonSelectionClickListener) {
        this.listener = listener
    }
}