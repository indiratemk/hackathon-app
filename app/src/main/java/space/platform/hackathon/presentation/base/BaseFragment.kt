package space.platform.hackathon.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import space.platform.hackathon.util.state.State
import space.platform.hackathon.util.state.StateListener
import space.platform.hackathon.util.ui.UIUtil

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