package com.example.hackathon.presentation.hackathon_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.base.BaseActivity
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.Constants
import com.example.hackathon.util.state.State
import com.example.hackathon.util.ui.DateFormat
import kotlinx.android.synthetic.main.activity_hackathon_detail.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.util.*

class HackathonDetailActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, id: Int, requestCode: Int) {
            val intent = Intent(activity, HackathonDetailActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, id)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    private val hackathonDetailViewModel: HackathonDetailViewModel by viewModel()
    private var hackathonId: Int? = null

    override fun layoutId() = R.layout.activity_hackathon_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        hackathonDetailViewModel.getHackathon(hackathonId!!)
    }

    private fun subscribeObservers() {
        hackathonDetailViewModel.hackathon.observe(this, Observer { dataState ->
            onStateChange(dataState)
            when(dataState) {
                is State.Success -> {
                    initToolbar(toolbar, dataState.data!!.title, false)
                    setDetailInfo(dataState.data)
                }
            }
        })
    }

    private fun setDetailInfo(hackathon: Hackathon) {
        Glide.with(this)
            .load(hackathon.thumbnailUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(ivHackathonImage)
        tvTitle.text = hackathon.title
        tvStartTime.text = DateFormat.getFormattedDate(hackathon.startDate.time, DateFormat.TIME_FORMAT_1)
        tvStartDate.text = DateFormat.getFormattedDate(hackathon.startDate.time, DateFormat.DATE_FORMAT_2)
        tvEndTime.text = DateFormat.getFormattedDate(hackathon.endDate.time, DateFormat.TIME_FORMAT_1)
        tvEndDate.text = DateFormat.getFormattedDate(hackathon.endDate.time, DateFormat.DATE_FORMAT_2)
        tvAddress.text = hackathon.address
        tvParticipantsCount.text = hackathon.numberOfParticipants
        markdownView.setMarkDownText(hackathon.fullDescription)
    }
}
