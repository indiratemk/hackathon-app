package space.platform.hackathon.presentation.profile.selection

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import space.platform.hackathon.R
import space.platform.hackathon.data.hackathon.model.Hackathon
import space.platform.hackathon.util.ui.DateFormat
import kotlinx.android.synthetic.main.vh_hackathon_selection.view.*

class HackathonSelectionVH(view: View) : RecyclerView.ViewHolder(view) {

    private val ivHackathonImage = view.ivHackathonImage
    private val tvTitle = view.tvTitle
    private val tvAddress = view.tvAddress
    private val tvDate = view.tvDate

    fun onBind(hackathon: Hackathon, listener: HackathonSelectionClickListener?) {
        Glide.with(itemView.context)
            .load(hackathon.thumbnailUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(ivHackathonImage)
        tvTitle.text = hackathon.title
        tvAddress.text = hackathon.address
        tvDate.text = DateFormat.getFormattedDate(hackathon.startDate.time, DateFormat.DATE_FORMAT_3)
        itemView.setOnClickListener { listener?.onHackathonClick(hackathon.id) }
    }
}