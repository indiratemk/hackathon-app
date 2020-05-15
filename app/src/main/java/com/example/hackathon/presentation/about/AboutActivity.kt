package com.example.hackathon.presentation.about

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_about.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class AboutActivity : BaseActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, AboutActivity::class.java)
            context.startActivity(intent)
        }
    }

    override fun layoutId() = R.layout.activity_about

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initToolbar(toolbar, getString(R.string.about_title), false)
        tvAntDesign.setOnClickListener { UIUtil.openSource(Constants.ANT_DESIGN_URL, this) }
        tvAntDesignSite.setOnClickListener { UIUtil.openSource(Constants.ANT_DESIGN_URL, this) }
        tvFreepik.setOnClickListener { UIUtil.openSource(Constants.FREEPIK_URL, this) }
        tvFreepikSite.setOnClickListener { UIUtil.openSource(Constants.FLATICONS_URL, this) }
        tvPixelPerfect.setOnClickListener { UIUtil.openSource(Constants.PIXEL_PERFECT_URL, this) }
        tvPixelPerfectSite.setOnClickListener { UIUtil.openSource(Constants.FLATICONS_URL, this) }
    }
}
