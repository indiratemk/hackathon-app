package com.example.hackathon.presentation.hackathons

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.hackathon.R
import com.example.hackathon.util.state.State
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.fragment_hackathons.*
import org.koin.android.viewmodel.ext.android.viewModel

class HackathonsFragment : Fragment() {

    companion object {
        fun newInstance(): HackathonsFragment {
            val args = Bundle()
            val fragment = HackathonsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val hackathonsViewModel: HackathonsViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hackathons, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        btnHackathons.setOnClickListener {
            hackathonsViewModel.getHackathons()
        }
    }

    private fun subscribeObservers() {
        hackathonsViewModel.hackathons.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is State.Success -> {
                    Log.d("taaaaag", "hackathons: ${dataState.data?.results}")
                }
                is State.NetworkError -> {
                    UIUtil.showErrorMessage(requireActivity(), dataState.message)
                }
                is State.BackendError -> {
                    UIUtil.showErrorMessage(requireActivity(), dataState.message)
                }
            }
        })
    }
}