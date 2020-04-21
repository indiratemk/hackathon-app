package com.example.hackathon.presentation.hackathon.detail

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.hackathon.R
import com.example.hackathon.data.hackathon.model.Hackathon
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.presentation.hackathon.participants.ParticipantsActivity
import com.example.hackathon.presentation.hackathon.registration.HackathonRegistrationActivity
import com.example.hackathon.presentation.sign_in.SignInActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.PreferenceUtils
import com.example.hackathon.util.ui.DateFormat
import com.example.hackathon.util.ui.UIUtil
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
    private var isParticipate = false

    override fun layoutId() = R.layout.activity_hackathon_detail

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        subscribeObservers()
        initUI()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == Constants.HACKATHON_REGISTRATION_REQUEST_CODE) {
                isParticipate = true
                updateParticipateButton()
            } else if (requestCode == Constants.AUTH_REQUEST_CODE) {
                hackathonDetailViewModel.checkParticipation(hackathonId!!)
            }
        }
    }

    private fun initUI() {
        if (PreferenceUtils.isAuthorized(this)) {
            hackathonDetailViewModel.checkParticipation(hackathonId!!)
        }
        hackathonDetailViewModel.getHackathon(hackathonId!!)
        initDescriptionDialog()
        initCriteriaDialog()
        initRulesDialog()
        btnParticipate.setOnClickListener {
            if (PreferenceUtils.isAuthorized(this)) {
                if (isParticipate) {
                    hackathonDetailViewModel.unregister(hackathonId!!)
                } else {
                    HackathonRegistrationActivity.startActivity(this, hackathonId!!,
                        Constants.HACKATHON_REGISTRATION_REQUEST_CODE)
                }
            } else {
                SignInActivity.startActivity(this, Constants.AUTH_REQUEST_CODE)
            }
        }
    }

    private fun subscribeObservers() {
        hackathonDetailViewModel.isParticipate.observe(this, Observer { dataState ->
            dataState.result?.let { result ->
                isParticipate = result.data
                updateParticipateButton()
            }
        })

        hackathonDetailViewModel.hackathon.observe(this, Observer { dataState ->
            setVisibilities(dataState.isLoading)
            dataState.result?.let { result ->
                val hackathon = result.data
                initToolbar(collapsedToolbar,
                    ContextCompat.getDrawable(this, R.drawable.ic_arrow_left)!!,
                    hackathon.title,
                    false)
                collapseBehavior(hackathon.title)
                setDetailInfo(hackathon)
            }
            onStateChange(dataState)
        })

        hackathonDetailViewModel.unregister.observe(this, Observer { dataState ->
            dataState.result?.let { result ->
                UIUtil.showSuccessMessage(this,
                    getString(R.string.hackathon_detail_success_unregister))
                isParticipate = false
                updateParticipateButton()
            }
            onStateChange(dataState)
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
        clParticipants.setOnClickListener {
            ParticipantsActivity.startActivity(this, hackathonId)
        }
        if (hackathon.description != null) mvDescription.setMarkDownText(hackathon.description) else clDescription.visibility = View.GONE
        if (hackathon.criteria != null) mvCriteria.setMarkDownText(hackathon.criteria) else clCriteria.visibility = View.GONE
        if (hackathon.rules != null) mvRules.setMarkDownText(hackathon.rules) else clRules.visibility = View.GONE
    }

    private fun collapseBehavior(title: String) {
        var isShow = true
        var scrollRange = -1
        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { barLayout, verticalOffset ->
            if (scrollRange == -1){
                scrollRange = barLayout?.totalScrollRange!!
            }
            if (scrollRange + verticalOffset == 0){
                tvTitle.visibility = View.GONE
                collapsingToolbar.title = title
                isShow = true
            } else if (isShow){
                tvTitle.visibility = View.VISIBLE
                collapsingToolbar.title = " "
                isShow = false
            }
        })
    }

    private fun updateParticipateButton() {
        btnParticipate.setBackgroundResource( if (isParticipate) R.drawable.shape_button_error else R.drawable.shape_button_primary_active)
        btnParticipate.text = if (isParticipate) getString(R.string.hackathon_detail_refuse_participation) else getString(R.string.hackathon_detail_participate)
    }

    private fun setVisibilities(isLoading: Boolean) {
        progressBar.visibility = if (isLoading) View.VISIBLE else View.GONE
        llContent.visibility = if (isLoading) View.GONE else View.VISIBLE
        btnParticipate.visibility = if (isLoading) View.GONE else View.VISIBLE
    }
}