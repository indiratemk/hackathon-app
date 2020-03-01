package com.example.hackathon.presentation.main

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.hackathon.R
import com.example.hackathon.base.BaseActivity
import com.example.hackathon.presentation.hackathons.HackathonsFragment
import com.example.hackathon.presentation.profile.ProfileFragment
import com.example.hackathon.presentation.qr_scanner.QRScannerActivity
import com.example.hackathon.presentation.sign_in.SignInActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.PreferenceUtils
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.layout_toolbar.*

class MainActivity : BaseActivity() {

    companion object {
        val HACKATHONS_FRAGMENT_POSITION = 0
        val PROFILE_FRAGMENT_POSITION = 1
    }

    private lateinit var bottomFragments: ArrayList<Fragment>
    private var selectedItemId = R.id.actionHackathons

    override fun layoutId(): Int {
        return R.layout.activity_main
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initToolbar(toolbar, getString(R.string.main_title))
        initBottomBar()
        bottomNavBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.actionQRScanner -> {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), Constants.CAMERA_REQUEST_CODE)
                    } else {
                        QRScannerActivity.startActivity(this, Constants.QR_SCANNER_REQUEST_CODE)
                    }
                }
                R.id.actionProfile -> {
                    if (PreferenceUtils.isAuthorized(this)) {
                        selectedItemId = R.id.actionProfile
                        setFragment(PROFILE_FRAGMENT_POSITION)
                    } else {
                        SignInActivity.startActivity(this, Constants.AUTH_REQUEST_CODE)
                    }
                }
                R.id.actionHackathons -> {
                    selectedItemId = R.id.actionHackathons
                    setFragment(HACKATHONS_FRAGMENT_POSITION)
                }
            }
            true
        }
        bottomNavBar.selectedItemId = selectedItemId
    }

    private fun initBottomBar() {
        bottomFragments = arrayListOf()
        val profileFragment = ProfileFragment.newInstance()
        val hackathonsFragment = HackathonsFragment.newInstance()
        bottomFragments.add(hackathonsFragment)
        bottomFragments.add(profileFragment)
    }

    private fun setFragment(position: Int) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.flMainContainer, bottomFragments.get(position))
            .commit()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == Constants.AUTH_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            selectedItemId = R.id.actionProfile
            bottomNavBar.selectedItemId = selectedItemId
        } else if (requestCode == Constants.QR_SCANNER_REQUEST_CODE || requestCode == Constants.AUTH_REQUEST_CODE) {
            bottomNavBar.selectedItemId = selectedItemId
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int,
                                            permissions: Array<out String>,
                                            grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == Constants.CAMERA_REQUEST_CODE && grantResults.get(0) == PERMISSION_GRANTED) {
            QRScannerActivity.startActivity(this, Constants.QR_SCANNER_REQUEST_CODE)
        }
    }
}
