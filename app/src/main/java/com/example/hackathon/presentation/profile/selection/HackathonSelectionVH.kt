package com.example.hackathon.presentation.profile.selection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Hackathon
import kotlinx.android.synthetic.main.vh_hackathon_selection.view.*

class HackathonSelectionVH(view: View) : RecyclerView.ViewHolder(view) {

    private val ivHackathonImage = view.ivHackathonImage
    private val tvTitle = view.tvTitle
    private val tvDescription = view.tvDescription

    fun onBind(hackathon: Hackathon) {
        Glide.with(itemView.context)
            .load(hackathon.thumbnailUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(ivHackathonImage)
        tvTitle.text = hackathon.title
        tvDescription.text = hackathon.description
    }
}