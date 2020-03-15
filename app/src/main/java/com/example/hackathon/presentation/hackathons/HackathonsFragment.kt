package com.example.hackathon.presentation.hackathons

import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.base.BaseFragment
import com.example.hackathon.util.state.State
import kotlinx.android.synthetic.main.fragment_hackathons.*
import org.koin.android.viewmodel.ext.android.viewModel

class HackathonsFragment : BaseFragment() {

    companion object {
        fun newInstance(): HackathonsFragment {
            val args = Bundle()
            val fragment = HackathonsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private val hackathonsViewModel: HackathonsViewModel by viewModel()
    private val hackathonsAdapter = HackathonsAdapter()

    override fun layoutId() = R.layout.fragment_hackathons

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorBlue))
        refreshLayout.setOnRefreshListener { hackathonsViewModel.getHackathons() }
        rvHackathons.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = hackathonsAdapter
        }
    }

    private fun subscribeObservers() {
        hackathonsViewModel.hackathons.observe(viewLifecycleOwner, Observer { dataState ->
            onStateChange(dataState)
            when (dataState) {
                is State.Loading -> {
                    refreshLayout.isRefreshing = dataState.isLoading
                }
                is State.Success -> {
                    hackathonsAdapter.setHackathons(dataState.data!!.results)
                }
            }
        })
    }
}