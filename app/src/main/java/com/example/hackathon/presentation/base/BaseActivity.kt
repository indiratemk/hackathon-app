package com.example.hackathon.presentation.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.example.hackathon.util.state.State
import com.example.hackathon.util.state.StateListener
import com.example.hackathon.util.ui.UIUtil

abstract class BaseActivity : AppCompatActivity(), StateListener {

    abstract fun layoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layoutId())
    }

    override fun onStateChange(dataState: State<*>) {
        handleStateChange(dataState)
    }

    private fun handleStateChange(dataState: State<*>) {
        when (dataState) {
            is State.NetworkError -> {
                UIUtil.showErrorMessage(this, dataState.message)
            }
            is State.BackendError -> {
                UIUtil.showErrorMessage(this, dataState.message)
            }
        }
    }

    fun initToolbar(toolbar: Toolbar, title: String, isHomeScreen: Boolean) {
        setSupportActionBar(toolbar)
        supportActionBar?.title = title
        if (!isHomeScreen) {
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}