package com.example.hackathon.presentation.hackathons

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.ui.DateFormat
import kotlinx.android.synthetic.main.vh_hackathon.view.*

class HackathonVH(view: View) : RecyclerView.ViewHolder(view) {

    private val ivHackathonImage = view.ivHackathonImage
    private val tvHackathonTitle = view.tvHackathonTitle
    private val tvHackathonAddress = view.tvHackathonAddress
    private val tvHackathonParticipants = view.tvHackathonParticipants
    private val tvHackathonDate = view.tvHackathonDate

    fun onBind(hackathon: Hackathon, listener: HackathonListener) {
        Glide.with(itemView.context)
            .load(hackathon.thumbnailUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(ivHackathonImage)
        tvHackathonTitle.text = hackathon.title
        tvHackathonAddress.text = hackathon.address
        tvHackathonParticipants.text = hackathon.numberOfParticipants
        tvHackathonDate.text = itemView.resources.getString(R.string.vh_hackathon_date,
            DateFormat.getFormattedDate(hackathon.startDate.time, DateFormat.DATE_FORMAT_1),
            DateFormat.getFormattedDate(hackathon.endDate.time, DateFormat.DATE_FORMAT_1))
        itemView.setOnClickListener { listener.onHackathonClick(hackathon.id) }
    }
}