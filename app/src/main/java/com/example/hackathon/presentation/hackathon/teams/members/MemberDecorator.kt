package com.example.hackathon.presentation.hackathon.teams.members

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class MemberDecorator(private val space: Int) : RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        val position = parent.getChildAdapterPosition(view)
        if (position != 0)
            outRect.right = space
    }
}