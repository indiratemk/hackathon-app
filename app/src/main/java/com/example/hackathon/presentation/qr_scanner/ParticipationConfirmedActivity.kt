package com.example.hackathon.presentation.qr_scanner

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.layout_toolbar.*

class ParticipationConfirmedActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, ParticipationConfirmedActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    override fun layoutId() = R.layout.activity_participation_confirmed

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initToolbar(toolbar, "Подтверждение участия", false)
    }
}
