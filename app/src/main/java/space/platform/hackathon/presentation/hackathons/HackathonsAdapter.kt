package space.platform.hackathon.presentation.hackathons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.platform.hackathon.R
import space.platform.hackathon.data.hackathon.model.Hackathon

class HackathonsAdapter(private val listener: HackathonListener) :
    RecyclerView.Adapter<HackathonVH>() {

    private var hackathons: MutableList<Hackathon> = emptyList<Hackathon>().toMutableList()
    private var isRefreshed = false

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackathonVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_hackathon, parent, false)
        return HackathonVH(view)
    }

    override fun onBindViewHolder(holder: HackathonVH, position: Int) {
        holder.onBind(hackathons.get(position), listener)
    }

    override fun getItemCount(): Int {
        return hackathons.size
    }

    fun addHackathons(hackathons: List<Hackathon>) {
        if (isRefreshed) {
            this.hackathons.clear()
            this.hackathons.addAll(hackathons)
            notifyDataSetChanged()
        } else {
            val beforeSize = this.hackathons.size
            this.hackathons.addAll(hackathons)
            notifyItemRangeInserted(beforeSize, hackathons.size)
        }
    }

    fun setRefreshed(isRefreshed: Boolean) {
        this.isRefreshed = isRefreshed
    }
}