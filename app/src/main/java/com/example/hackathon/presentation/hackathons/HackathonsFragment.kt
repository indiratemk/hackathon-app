package com.example.hackathon.presentation.hackathons

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.presentation.base.BaseFragment
import com.example.hackathon.presentation.hackathon.detail.HackathonDetailActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.state.State
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.fragment_hackathons.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class HackathonsFragment : BaseFragment(), HackathonListener {

    companion object {
        fun newInstance(): HackathonsFragment {
            val args = Bundle()
            val fragment = HackathonsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private lateinit var searchView: SearchView
    private val hackathonsViewModel: HackathonsViewModel by viewModel()
    private val hackathonsAdapter = HackathonsAdapter(this)

    override fun layoutId() = R.layout.fragment_hackathons

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        subscribeObservers()
        initUI()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.hackathons_menu, menu)
        val searchItem = menu.findItem(R.id.actionSearch)
        searchView = searchItem.actionView as SearchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(searchText: String): Boolean {
                hackathonsViewModel.searchHackathons(searchText)
                return true
            }
        })
    }

    private fun initUI() {
        (requireActivity() as BaseActivity).initToolbar(toolbar,
            getString(R.string.hackathons_title), true)
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
                    hackathonsAdapter.setHackathons(dataState.result!!.data)
                }
            }
        })

        hackathonsViewModel.search.observe(viewLifecycleOwner, Observer { dataState ->
            when (dataState) {
                is State.Loading -> {
                    refreshLayout.isRefreshing = dataState.isLoading
                }
                is State.Success -> {
                    rvHackathons.visibility = View.VISIBLE
                    tvSearchError.visibility = View.GONE
                    hackathonsAdapter.setHackathons(dataState.result!!.data)
                }
                is State.BackendError -> {
                    if (dataState.errorCode == 404) {
                        rvHackathons.visibility = View.GONE
                        tvSearchError.visibility = View.VISIBLE
                        tvSearchError.text = dataState.message
                    } else {
                        UIUtil.showErrorMessage(requireActivity(), dataState.message)
                    }
                }
                is State.NetworkError -> {
                    UIUtil.showErrorMessage(requireActivity(), dataState.message)
                }
            }
        })
    }

    override fun onHackathonClick(id: Int) {
        HackathonDetailActivity.startActivity(requireActivity(), id, Constants.HACKATHON_DETAIL_REQUEST_CODE)
    }
}