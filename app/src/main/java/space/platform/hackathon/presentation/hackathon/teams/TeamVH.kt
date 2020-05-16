package space.platform.hackathon.presentation.hackathon.teams

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import space.platform.hackathon.R
import space.platform.hackathon.data.hackathon.model.Team
import space.platform.hackathon.presentation.hackathon.teams.members.MemberDecorator
import space.platform.hackathon.presentation.hackathon.teams.members.MembersAdapter
import space.platform.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.vh_team.view.*

class TeamVH(view: View) : RecyclerView.ViewHolder(view) {

    private var tvTitle = view.tvTitle
    private var tvParticipantsCount = view.tvParticipantsCount
    private var rvMembers = view.rvMembers
    private val membersAdapter =
        MembersAdapter()

    fun onBind(team: Team) {
        tvTitle.text = team.name
        team.members?.let {
            tvParticipantsCount.text = itemView.resources
                .getQuantityString(R.plurals.participants_plurals, team.members.size, team.members.size)

            rvMembers.apply {
                setHasFixedSize(true)
                val membersLayout = LinearLayoutManager(itemView.context, RecyclerView.HORIZONTAL, true)
                membersLayout.stackFromEnd = true
                layoutManager = membersLayout
                adapter = membersAdapter
            }

            rvMembers.addItemDecoration(
                MemberDecorator(
                    UIUtil.dpToPx(-12)
                )
            )
            membersAdapter.setMembers(it.reversed())
        }
    }
}