package com.example.hackathon.presentation.hackathon.participants

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.hackathon.R
import com.example.hackathon.presentation.base.BaseActivity
import com.example.hackathon.presentation.profile.ProfileFragment
import com.example.hackathon.util.Constants

class ParticipantsActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, id: Int?) {
            val intent = Intent(activity, ParticipantsActivity::class.java)
            intent.putExtra(Constants.HACKATHON_ID_EXTRA, id)
            activity.startActivity(intent)
        }
    }

    private var hackathonId: Int? = null
    private lateinit var fragmentManager: FragmentManager
    private lateinit var participantsListFragment: ParticipantsListFragment

    override fun layoutId() = R.layout.activity_participants

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        hackathonId = intent.getIntExtra(Constants.HACKATHON_ID_EXTRA, -1)
        fragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        participantsListFragment = ParticipantsListFragment.newInstance(hackathonId!!)
        fragmentTransaction.replace(R.id.fragmentContainer, participantsListFragment)
        fragmentTransaction.commit()
    }

    fun openParticipantProfile (email: String) {
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        val profileFragment = ProfileFragment.newInstance(email)
        fragmentTransaction.hide(participantsListFragment)
        fragmentTransaction.add(R.id.fragmentContainer, profileFragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}