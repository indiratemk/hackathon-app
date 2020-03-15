package com.example.hackathon.presentation.hackathons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
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
    private val hackathonsAdapter = HackathonsAdapter()

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
            when (dataState) {
                is State.Loading -> {
                    refreshLayout.isRefreshing = dataState.isLoading
                }
                is State.Success -> {
                    hackathonsAdapter.setHackathons(dataState.data!!.results)
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