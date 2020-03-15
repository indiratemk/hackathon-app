package com.example.hackathon.presentation.hackathons

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Hackathon

class HackathonsAdapter :
    RecyclerView.Adapter<HackathonVH>() {

    private var hackathons: List<Hackathon> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HackathonVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_hackathon, parent, false)
        return HackathonVH(view)
    }

    override fun onBindViewHolder(holder: HackathonVH, position: Int) {
        holder.onBind(hackathons.get(position))
    }

    override fun getItemCount(): Int {
        return hackathons.size
    }

    fun setHackathons(hackathons: List<Hackathon>) {
        this.hackathons = hackathons
        notifyDataSetChanged()
    }
}