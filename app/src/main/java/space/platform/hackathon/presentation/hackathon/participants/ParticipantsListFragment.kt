package space.platform.hackathon.presentation.hackathon.participants

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import space.platform.hackathon.R
import space.platform.hackathon.presentation.base.BaseActivity
import space.platform.hackathon.presentation.base.BaseFragment
import space.platform.hackathon.util.Constants
import space.platform.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.fragment_participants_list.*
import kotlinx.android.synthetic.main.layout_empty_list.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class ParticipantsListFragment : BaseFragment(), ParticipantClickListener {

    companion object {
        fun newInstance(id: Int): ParticipantsListFragment {
            val args = Bundle()
            val fragment = ParticipantsListFragment()
            args.putInt(Constants.HACKATHON_ID_EXTRA, id)
            fragment.arguments = args
            return fragment
        }
    }

    private val participantsViewModel: ParticipantsViewModel by viewModel()
    private val participantsAdapter = ParticipantsAdapter(this)

    override fun layoutId() = R.layout.fragment_participants_list

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let {
            val hackathonId = it.getInt(Constants.HACKATHON_ID_EXTRA)
            participantsViewModel.getParticipants(hackathonId)
        }
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        (requireActivity() as BaseActivity).initToolbar(toolbar, getString(R.string.participants_title), false)
        initRV()
    }

    private fun initRV() {
        rvParticipants.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = participantsAdapter
        }
    }

    private fun subscribeObservers() {
        participantsViewModel.participants.observe(viewLifecycleOwner, Observer { dataState ->
            progressBar.visibility = if (dataState.isLoading) View.VISIBLE else View.GONE
            onStateChange(dataState)
            dataState.result?.let { result ->
                if (result.data.isEmpty()) {
                    llEmptyList.visibility = View.VISIBLE
                    tvEmptyListMessage.text = getString(R.string.participants_no_participants)
                    rvParticipants.visibility = View.GONE
                } else {
                    participantsAdapter.setParticipants(result.data)
                }
            }
        })

        participantsViewModel.currentUser.observe(viewLifecycleOwner, Observer { participant ->
            participantsAdapter.setCurrentUser(participant)
        })

        participantsViewModel.notification.observe(viewLifecycleOwner, Observer { dataState ->
            onStateChange(dataState)
            dataState.result?.let { result ->
                UIUtil.showSuccessMessage(requireActivity(), getString(R.string.participants_invite_sent))
            }
        })
    }

    override fun onParticipantClick(email: String) {
        (requireActivity() as ParticipantsActivity).openParticipantProfile(email)
    }

    override fun onInviteClick(receiverId: Int, teamId: Int) {
        participantsViewModel.inviteParticipant(receiverId, teamId)
    }
}