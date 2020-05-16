package space.platform.hackathon.presentation.main

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import space.platform.hackathon.R
import space.platform.hackathon.presentation.base.BaseActivity
import space.platform.hackathon.presentation.hackathons.HackathonsFragment
import space.platform.hackathon.presentation.profile.ProfileFragment
import space.platform.hackathon.presentation.qr_scanner.QRScannerActivity
import space.platform.hackathon.presentation.sign_in.SignInActivity
import space.platform.hackathon.util.Constants
import space.platform.hackathon.util.PreferenceUtils
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    companion object {
        val HACKATHONS_FRAGMENT_POSITION = 0
        val PROFILE_FRAGMENT_POSITION = 1
    }

    private var selectedItemId = R.id.actionHackathons
    private lateinit var bottomPagerAdapter: BottomPagerAdapter

    override fun layoutId() = R.layout.activity_main

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initUI()
    }

    private fun initUI() {
        initBottomBar()
        updateBottomBar()
        bottomNavBar.setOnNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.actionQRScanner -> {
                    if (PreferenceUtils.isAuthorized(this)) {
                        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_DENIED) {
                            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), Constants.CAMERA_REQUEST_CODE)
                        } else {
                            QRScannerActivity.startActivity(this, Constants.QR_SCANNER_REQUEST_CODE)
                        }
                    } else {
                        SignInActivity.startActivity(this, Constants.AUTH_REQUEST_CODE)
                    }
                }
                R.id.actionProfile -> {
                    if (PreferenceUtils.isAuthorized(this)) {
                        selectedItemId = R.id.actionProfile
                        viewPager.currentItem = PROFILE_FRAGMENT_POSITION
                    } else {
                        SignInActivity.startActivity(this, Constants.AUTH_REQUEST_CODE)
                    }
                }
                R.id.actionHackathons -> {
                    selectedItemId = R.id.actionHackathons
                    viewPager.currentItem = HACKATHONS_FRAGMENT_POSITION
                }
            }
            true
        }
        bottomNavBar.selectedItemId = selectedItemId
    }

    private fun updateBottomBar() {
        if (PreferenceUtils.isAuthorized(this)) {
            bottomPagerAdapter.addFragment(ProfileFragment.newInstance())
            bottomPagerAdapter.notifyDataSetChanged()
        }
    }

    private fun initBottomBar() {
        bottomPagerAdapter =
            BottomPagerAdapter(supportFragmentManager)
        bottomPagerAdapter.addFragment(HackathonsFragment.newInstance())
        viewPager.adapter = bottomPagerAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        updateBottomBar()
        if (requestCode == Constants.AUTH_REQUEST_CODE || requestCode == Constants.QR_SCANNER_REQUEST_CODE) {
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
