package com.example.hackathon.presentation.hackathons

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.hackathon.R

class HackathonsFragment : Fragment() {

    companion object {
        fun newInstance(): HackathonsFragment {
            val args = Bundle()
            val fragment = HackathonsFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hackathons, container, false)
    }
}