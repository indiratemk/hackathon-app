package com.example.hackathon.presentation.hackathon.detail.tags

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Tag
import kotlinx.android.synthetic.main.vh_tag.view.*

class TagVH(view: View) : RecyclerView.ViewHolder(view) {

    private val tvTag = view.tvTag

    fun onBind(tag: Tag) {
        tvTag.text = itemView.context.getString(R.string.hackathon_detail_tag_title, tag.title)
    }
}