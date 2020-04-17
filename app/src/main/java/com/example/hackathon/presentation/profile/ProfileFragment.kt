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
import com.example.hackathon.presentation.logout.LogoutViewModel
import com.example.hackathon.presentation.profile.selection.HackathonSelectionAdapter
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment() {

    companion object {
        fun newInstance(): ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val logoutViewModel: LogoutViewModel by viewModel()
    private val userViewModel: ProfileViewModel by viewModel()
    private val pastHackathonsAdapter = HackathonSelectionAdapter()
    private val currentHackathonsAdapter = HackathonSelectionAdapter()

    override fun layoutId() = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        refreshLayout.setOnRefreshListener { userViewModel.getUser() }
        ivLogout.setOnClickListener { logoutViewModel.logout() }
        initRV()
    }

    private fun initRV() {
        rvPastHackathons.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = pastHackathonsAdapter
        }

        rvCurrentHackathons.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = currentHackathonsAdapter
        }
    }

    private fun subscribeObservers() {
        userViewModel.user.observe(viewLifecycleOwner, Observer { dataState ->
            refreshLayout.isRefreshing = dataState.isLoading
            dataState.result?.let { result ->
                val user = result.data
                userViewModel.getCurrentHackathons(user.id)
                userViewModel.getPastHackathons(user.id)
                setUserDetails(user)
            }
        })

        logoutViewModel.logout.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.result?.let {
                (requireActivity().application as HackathonApp).restartApp()
            }
            onStateChange(dataState)
        })

        userViewModel.currentHackathons.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.result?.let { result ->
                currentHackathonsAdapter.setHackathons(result.data)
            }
            onStateChange(dataState)
        })

        userViewModel.pastHackathons.observe(viewLifecycleOwner, Observer { dataState ->
            dataState.result?.let { result ->
                pastHackathonsAdapter.setHackathons(result.data)
            }
        })
    }

    private fun setUserDetails(user: User) {
        Glide.with(this)
            .load(user.avatarUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(civAvatar)
        tvFullName.text = getString(R.string.profile_full_name,
            user.name, user.surname)
        tvEmail.text = user.email
    }
}