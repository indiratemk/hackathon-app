package com.example.hackathon.presentation.hackathons

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Hackathon
import kotlinx.android.synthetic.main.vh_hackathon.view.*

class HackathonVH(view: View) : RecyclerView.ViewHolder(view) {

    private val ivHackathonImage = view.ivHackathonImage
    private val tvHackathonTitle = view.tvHackathonTitle
    private val tvHackathonAddress = view.tvHackathonAddress

    fun onBind(hackathon: Hackathon) {
        Glide.with(itemView.context)
            .load(hackathon.thumbnailUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(ivHackathonImage)
        tvHackathonTitle.text = hackathon.title
        tvHackathonAddress.text = hackathon.address
    }
}