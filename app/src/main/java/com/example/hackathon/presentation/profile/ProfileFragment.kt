package com.example.hackathon.presentation.profile

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.hackathon.HackathonApp
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseFragment
import com.example.hackathon.presentation.logout.LogoutViewModel
import com.example.hackathon.util.state.State
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

    override fun layoutId() = R.layout.fragment_profile

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        ivLogout.setOnClickListener { logoutViewModel.logout() }
    }

    private fun subscribeObservers() {
        userViewModel.user.observe(viewLifecycleOwner, Observer { dataState ->
            onStateChange(dataState)
            when (dataState) {
                is State.Success -> {
                    Glide.with(this)
                        .load(dataState.result?.data?.avatarUrl)
                        .placeholder(R.drawable.img_hackathon_no_image)
                        .error(R.drawable.img_hackathon_no_image)
                        .into(civAvatar)
                    tvFullName.text = getString(R.string.profile_full_name,
                        dataState.result?.data?.name, dataState.result?.data?.surname)
                    tvEmail.text = dataState.result?.data?.email
                }
            }
        })

        logoutViewModel.logout.observe(viewLifecycleOwner, Observer { dataState ->
            onStateChange(dataState)
            when (dataState) {
                is State.Success -> {
                    (requireActivity().application as HackathonApp).restartApp()
                }
            }
        })
    }
}