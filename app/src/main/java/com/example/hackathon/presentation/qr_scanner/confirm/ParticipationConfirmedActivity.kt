package com.example.hackathon.presentation.qr_scanner.confirm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.presentation.hackathon.detail.HackathonDetailActivity
import com.example.hackathon.util.Constants
import kotlinx.android.synthetic.main.activity_participation_confirmed.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class ParticipationConfirmedActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, hackathonId: Int, requestCode: Int) {
            val intent = Intent(activity, ParticipationConfirmedActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, hackathonId)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    private var hackathonId: Int? = null

    override fun layoutId() = R.layout.activity_participation_confirmed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        initUI()
    }

    private fun initUI() {
        initToolbar(toolbar, getString(R.string.hackathon_confirmation_title), false)
        btnToHackathon.setOnClickListener {
            HackathonDetailActivity.startActivity(this,
                hackathonId!!, Constants.HACKATHON_DETAIL_REQUEST_CODE)
            finish()
        }
    }
}
