package com.example.hackathon.presentation.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.hackathon.R
import com.example.hackathon.util.state.State
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.fragment_profile.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.prefs.Preferences

class ProfileFragment : Fragment() {

    companion object {
        fun newInstance(): ProfileFragment {
            val args = Bundle()
            val fragment = ProfileFragment()
            fragment.arguments = args
            return fragment
        }
    }

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
        userViewModel.getUser()
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
    }
}