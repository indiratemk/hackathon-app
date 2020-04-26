package com.example.hackathon.presentation.hackathon.registration

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.lifecycle.Observer
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_hackathon_detail.*
import kotlinx.android.synthetic.main.activity_hackathon_registration.*
import kotlinx.android.synthetic.main.layout_toolbar.*
import org.koin.android.viewmodel.ext.android.viewModel

class HackathonRegistrationActivity : BaseActivity() {

    private val hackathonRegistrationViewModel: HackathonRegistrationViewModel by viewModel()
    private var hackathonId: Int? = null
    private var type: Int = Constants.STANDALONE

    override fun layoutId() = R.layout.activity_hackathon_registration

    companion object {
        fun startActivity(activity: Activity, id: Int, requestCode: Int) {
            val intent = Intent(activity, HackathonRegistrationActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, id)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        subscribeObservers()
        initUI()
    }

    fun initUI() {
        initToolbar(toolbar, getString(R.string.hackathon_registration_title), false)
        rgParticipateMethods.setOnCheckedChangeListener{group, checkedId -> run {
                when (checkedId) {
                    R.id.rbStandalone -> type = Constants.STANDALONE
                    R.id.rbSearchingForTeam -> type = Constants.SEARCHING_FOR_TEAM
                    R.id.rbWithTeam -> type = Constants.WITH_TEAM
                }
            } }
        btnConfirmParticipation.setOnClickListener { hackathonRegistrationViewModel.register(hackathonId!!, type) }
    }

    private fun subscribeObservers() {
        hackathonRegistrationViewModel.isRegistered.observe(this, Observer { dataState ->
            dataState.result?.let {
                UIUtil.showSuccessMessage(this, getString(R.string.hackathon_registration_success_registration))
                setResult(Activity.RESULT_OK)
                finish()
            }
            onStateChange(dataState)
        })
    }
}
