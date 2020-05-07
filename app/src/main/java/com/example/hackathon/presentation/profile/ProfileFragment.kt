package com.example.hackathon.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.hackathon.HackathonApp
import com.example.hackathon.R
import com.example.hackathon.data.auth.model.User
import com.example.hackathon.presentation.base.BaseFragment
import com.example.hackathon.presentation.hackathon.detail.HackathonDetailActivity
import com.example.hackathon.presentation.logout.LogoutViewModel
import com.example.hackathon.presentation.profile.selection.HackathonSelectionAdapter
import com.example.hackathon.presentation.profile.selection.HackathonSelectionClickListener
import com.example.hackathon.util.Constants
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment(), HackathonSelectionClickListener {

    companion object {
        fun newInstance(): ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }

        fun newInstance(email: String): ProfileFragment {
            val args = Bundle()
            args.putString(Constants.USER_EMAIL_EXTRA, email)
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var email: String? = null
    private val logoutViewModel: LogoutViewModel by viewModel()
    private val userViewModel: ProfileViewModel by viewModel()
    private val participatedHackathonsAdapter = HackathonSelectionAdapter()
    private val ownHackathonsAdapter = HackathonSelectionAdapter()

    override fun layoutId() = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        arguments?.let { bundle ->
            email = bundle.getString(Constants.USER_EMAIL_EXTRA)
            if (email != null) {
                ivBack.visibility = View.VISIBLE
                ivNotification.visibility = View.GONE
                userViewModel.getUserByEmail(email!!)
            } else {
                ivBack.visibility = View.GONE
                ivNotification.visibility = View.VISIBLE
                userViewModel.getCurrentUser()
                userViewModel.getNotificationsCount()
            }
        }
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        refreshLayout.setOnRefreshListener {
            if (email == null) {
                userViewModel.getNotificationsCount()
                userViewModel.getCurrentUser()
            } else {
                userViewModel.getUserByEmail(email!!)
            }
        }
        ivBack.setOnClickListener { requireActivity().onBackPressed() }
        ivLogout.setOnClickListener { logoutViewModel.logout() }
        initRV()
    }

    private fun initRV() {
        participatedHackathonsAdapter.setHackathonListener(this)
        rvParticipatedHackathons.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = participatedHackathonsAdapter
        }

        ownHackathonsAdapter.setHackathonListener(this)
        rvOwnHackathons.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = ownHackathonsAdapter
        }
    }

    private fun subscribeObservers() {
        userViewModel.user.observe(viewLifecycleOwner, Observer { dataState ->
            refreshLayout.isRefreshing = dataState.isLoading
            dataState.result?.let { result ->
                val user = result.data
                userViewModel.getParticipatedHackathons(user.id)
                userViewModel.getOwnHackathons(user.id)
                setUserDetails(user)
            }
        })

        userViewModel.notificationsCount.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.result?.let { result ->
                result.data["count"]?.let { count ->
                    if (count > 0) {
                        tvNotificationCount.text = count.toString()
                        tvNotificationCount.visibility = View.VISIBLE
                    }
                }
            }
        })

        logoutViewModel.logout.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.result?.let {
                (requireActivity().application as HackathonApp).restartApp()
            }
            onStateChange(dataState)
        })

        userViewModel.participatedHackathons.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.result?.let { result ->
                tvParticipatedHackathons.visibility = if (result.data.isEmpty()) View.GONE else View.VISIBLE
                participatedHackathonsAdapter.setHackathons(result.data)
            }
            onStateChange(dataState)
        })

        userViewModel.ownHackathons.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.result?.let { result ->
                tvOwnHackathons.visibility = if (result.data.isEmpty()) View.GONE else View.VISIBLE
                ownHackathonsAdapter.setHackathons(result.data)
            }
        })
    }

    private fun setUserDetails(user: User) {
        Glide.with(this)
            .load(user.avatarUrl)
            .placeholder(R.drawable.no_avatar)
            .error(R.drawable.no_avatar)
            .into(civAvatar)
        tvFullName.text = getString(R.string.profile_full_name,
            user.name, user.surname)
        tvEmail.text = user.email
    }

    override fun onHackathonClick(hackathonId: Int) {
        HackathonDetailActivity.startActivity(requireActivity(),
            hackathonId, Constants.HACKATHON_DETAIL_REQUEST_CODE)
    }
}