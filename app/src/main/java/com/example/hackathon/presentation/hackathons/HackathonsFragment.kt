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
        (requireActivity() as BaseActivity).initToolbar(toolbar, getString(R.string.hackathons_title), true)
        refreshLayout.setColorSchemeColors(ContextCompat.getColor(requireContext(), R.color.colorBlue300))
        refreshLayout.setOnRefreshListener { hackathonsViewModel.getHackathons() }
        initRV()
    }

    private fun initRV() {
        rvHackathons.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(requireContext())
            adapter = hackathonsAdapter
        }
    }

    private fun subscribeObservers() {
        hackathonsViewModel.hackathons.observe(viewLifecycleOwner, Observer { dataState ->
            onStateChange(dataState)
            refreshLayout.isRefreshing = dataState.isLoading
            dataState.result?.let { result ->
                hackathonsAdapter.setHackathons(result.data)
            }
        })

        hackathonsViewModel.search.observe(viewLifecycleOwner, Observer { dataState ->
            refreshLayout.isRefreshing = dataState.isLoading
            dataState.result?.let { result ->
                hideSearchingError()
                hackathonsAdapter.setHackathons(result.data)
            }
            dataState.message?.let { message ->
                if (dataState.errorCode == Constants.NOT_FOUND_ERROR_CODE) {
                    showSearchingError()
                    tvSearchError.text = message
                } else {
                    UIUtil.showErrorMessage(requireActivity(), message)
                }
            }
        })
    }

    fun showSearchingError() {
        rvHackathons.visibility = View.GONE
        tvSearchError.visibility = View.VISIBLE
    }

    fun hideSearchingError() {
        rvHackathons.visibility = View.VISIBLE
        tvSearchError.visibility = View.GONE
    }

    override fun onHackathonClick(id: Int) {
        HackathonDetailActivity.startActivity(requireActivity(), id, Constants.HACKATHON_DETAIL_REQUEST_CODE)
    }
}