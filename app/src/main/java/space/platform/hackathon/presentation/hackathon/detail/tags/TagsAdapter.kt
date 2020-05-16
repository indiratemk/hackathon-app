package space.platform.hackathon.presentation.hackathon.detail.tags

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import space.platform.hackathon.R
import space.platform.hackathon.data.hackathon.model.Tag

class TagsAdapter(private val tags: List<Tag>) : RecyclerView.Adapter<TagVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TagVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.vh_tag, parent, false)
        return TagVH(view)
    }

    override fun onBindViewHolder(holder: TagVH, position: Int) {
        holder.onBind(tags.get(position))
    }

    override fun getItemCount(): Int = tags.size
}