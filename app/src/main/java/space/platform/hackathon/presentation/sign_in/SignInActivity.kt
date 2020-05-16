package space.platform.hackathon.presentation.sign_in

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.Observer
import space.platform.hackathon.R
import space.platform.hackathon.presentation.base.BaseActivity
import space.platform.hackathon.presentation.sign_up.SignUpActivity
import space.platform.hackathon.util.Constants
import space.platform.hackathon.util.PreferenceUtils
import space.platform.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.android.viewmodel.ext.android.viewModel

class SignInActivity : BaseActivity() {

    companion object {
        fun startActivity(activity: Activity, requestCode: Int) {
            val intent = Intent(activity, SignInActivity::class.java)
            activity.startActivityForResult(intent, requestCode)
        }
    }

    private val signInViewModel: SignInViewModel by viewModel()

    override fun layoutId(): Int {
        return R.layout.activity_sign_in
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.LightTheme)
        super.onCreate(savedInstanceState)
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        llSignIn.setOnClickListener {
            validateEmail()
            validatePassword()
            if (tilEmail.error == null && tilPassword.error == null) {
                signInViewModel.signIn(tietEmail.text.toString().trim(),
                    tietPassword.text.toString().trim())
                UIUtil.hideKeyboard(this)
            }
        }

        tvSignUp.setOnClickListener {
            finish()
            SignUpActivity.startActivity(this, Constants.AUTH_REQUEST_CODE)
        }

        ivClose.setOnClickListener { finish() }
    }

    private fun subscribeObservers() {
        signInViewModel.user.observe(this, Observer { dataState ->
            showProgress(dataState.isLoading)
            dataState.result?.let { result ->
                PreferenceUtils.setAuthorized(this, true)
                PreferenceUtils.setUserId(this, result.data.id)
                setResult(Activity.RESULT_OK)
                finish()
            }
            onStateChange(dataState)
        })
    }

    private fun showProgress(isLoading: Boolean) {
        if (isLoading) {
            ivButtonProgress.visibility = View.VISIBLE
            llSignIn.isEnabled = false
        } else {
            ivButtonProgress.visibility = View.GONE
            llSignIn.isEnabled = true
        }
    }

    private fun validateEmail() {
        val email = tietEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.isErrorEnabled = true
            tilEmail.error = getString(R.string.authorization_empty_email_error)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.isErrorEnabled = true
            tilEmail.error = getString(R.string.authorization_invalid_email_error)
        } else {
            tilEmail.isErrorEnabled = false
            tilEmail.error = null
        }
    }

    private fun validatePassword() {
        val password = tietPassword.text.toString().trim()
        if (password.isEmpty()) {
            tilPassword.isErrorEnabled = true
            tilPassword.error = getString(R.string.authorization_empty_password)
        } else {
            tilPassword.isErrorEnabled = false
            tilPassword.error = null
        }
    }
}