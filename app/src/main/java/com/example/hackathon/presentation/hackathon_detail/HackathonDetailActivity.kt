package com.example.hackathon.presentation.hackathon_detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.base.BaseActivity
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.util.Constants
import com.example.hackathon.util.state.State
import com.example.hackathon.util.ui.DateFormat
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.mukesh.MarkdownView
import kotlinx.android.synthetic.main.activity_hackathon_detail.*
import org.koin.android.viewmodel.ext.android.viewModel

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
    private lateinit var mvDescription: MarkdownView
    private lateinit var mvCriteria: MarkdownView
    private lateinit var mvRules: MarkdownView

    override fun layoutId() = R.layout.activity_hackathon_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.TransparentTheme)
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        hackathonDetailViewModel.getHackathon(hackathonId!!)
        initDescriptionDialog()
        initCriteriaDialog()
        initRulesDialog()
    }

    private fun subscribeObservers() {
        hackathonDetailViewModel.hackathon.observe(this, Observer { dataState ->
            onStateChange(dataState)
            when(dataState) {
                is State.Success -> {
                    val hackathon = dataState.data!!
                    initToolbar(collapsedToolbar, hackathon.title, false)
                    collapseBehavior()
                    setDetailInfo(hackathon)
                }
            }
        })
    }

    private fun initDescriptionDialog() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_description, null)
        val descriptionBottomDialog = BottomSheetDialog(this, R.style.RoundedBottomSheetDialogTheme)
        descriptionBottomDialog.setContentView(view)
        mvDescription = view.findViewById(R.id.mvDescription)
        clDescription.setOnClickListener { descriptionBottomDialog.show() }
    }

    private fun initCriteriaDialog() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_criteria, null)
        val criteriaBottomDialog = BottomSheetDialog(this, R.style.RoundedBottomSheetDialogTheme)
        criteriaBottomDialog.setContentView(view)
        mvCriteria = view.findViewById(R.id.mvCriteria)
        clCriteria.setOnClickListener { criteriaBottomDialog.show() }
    }

    private fun initRulesDialog() {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_rules, null)
        val rulesBottomDialog = BottomSheetDialog(this, R.style.RoundedBottomSheetDialogTheme)
        rulesBottomDialog.setContentView(view)
        mvRules = view.findViewById(R.id.mvRules)
        clRules.setOnClickListener { rulesBottomDialog.show() }
    }

    private fun setDetailInfo(hackathon: Hackathon) {
        Glide.with(this)
            .load(hackathon.thumbnailUrl)
            .placeholder(R.drawable.img_hackathon_no_image)
            .error(R.drawable.img_hackathon_no_image)
            .into(ivBackdrop)
        tvTitle.text = hackathon.title
        tvStartTime.text = DateFormat.getFormattedDate(hackathon.startDate.time, DateFormat.TIME_FORMAT_1)
        tvStartDate.text = DateFormat.getFormattedDate(hackathon.startDate.time, DateFormat.DATE_FORMAT_2)
        tvEndTime.text = DateFormat.getFormattedDate(hackathon.endDate.time, DateFormat.TIME_FORMAT_1)
        tvEndDate.text = DateFormat.getFormattedDate(hackathon.endDate.time, DateFormat.DATE_FORMAT_2)
        tvAddress.text = hackathon.address
        tvParticipantsCount.text = hackathon.numberOfParticipants
        if (hackathon.description != null) mvDescription.setMarkDownText(hackathon.description) else clDescription.visibility = View.GONE
        if (hackathon.criteria != null) mvCriteria.setMarkDownText(hackathon.criteria) else clCriteria.visibility = View.GONE
        if (hackathon.rules != null) mvRules.setMarkDownText(hackathon.rules) else clRules.visibility = View.GONE
    }

    private fun collapseBehavior() {
        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                tvTitle.visibility = View.GONE
                isShow = true
            } else if (isShow){
                tvTitle.visibility = View.VISIBLE
                isShow = false
            }
        })
    }
}
