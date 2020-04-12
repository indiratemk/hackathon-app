package com.example.hackathon.presentation.profile.selection

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Hackathon

class HackathonSelectionAdapter : RecyclerView.Adapter<HackathonSelectionVH>() {

    private var hackathons: List<Hackathon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackathonSelectionVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_hackathon_selection, parent, false)
        return HackathonSelectionVH(view)
    }

    override fun onBindViewHolder(holder: HackathonSelectionVH, position: Int) {
        holder.onBind(hackathons.get(position))
    }

    override fun getItemCount() = hackathons.size

    fun setHackathons(hackathons: List<Hackathon>) {
        this.hackathons = hackathons
        notifyDataSetChanged()
    }
}