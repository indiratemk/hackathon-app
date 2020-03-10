package com.example.hackathon.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.hackathon.HackathonApp
import com.example.hackathon.R
import com.example.hackathon.presentation.logout.LogoutViewModel
import com.example.hackathon.util.state.State
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel

class ProfileFragment : Fragment() {

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

    override fun onCreateView(inflater: LayoutInflater,
                              container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        btnLogOut.setOnClickListener { logoutViewModel.logout() }
    }

    private fun subscribeObservers() {
        userViewModel.user.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is State.Success -> {
                    tvLogin.text = dataState.data?.login
                    tvEmail.text = dataState.data?.email
                }
                is State.NetworkError -> {
                    activity?.let { UIUtil.showErrorMessage(it, dataState.message) }
                }
                is State.BackendError -> {
                    activity?.let { UIUtil.showErrorMessage(it, dataState.message) }
                }
            }
        })

        logoutViewModel.logout.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is State.Success -> {
                    (requireActivity().application as HackathonApp).restartApp()
                }
                is State.NetworkError -> {
                    activity?.let { UIUtil.showErrorMessage(it, dataState.message) }
                }
                is State.BackendError -> {
                    activity?.let { UIUtil.showErrorMessage(it, dataState.message) }
                }
            }
        })
    }
}