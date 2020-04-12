package com.example.hackathon.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hackathon.util.state.State
import com.example.hackathon.util.state.StateListener
import com.example.hackathon.util.ui.UIUtil

abstract class BaseFragment : Fragment(), StateListener {

    abstract fun layoutId(): Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId(), container, false)
    }

    override fun onStateChange(dataState: State<*>) {
        handleStateChange(dataState)
    }

    private fun handleStateChange(dataState: State<*>) {
        dataState.message?.let { message ->
            UIUtil.showErrorMessage(requireActivity(), message)
        }
    }
}