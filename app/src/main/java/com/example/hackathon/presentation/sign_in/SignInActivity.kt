package com.example.hackathon.presentation.sign_in

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.Observer
import com.example.hackathon.R
import com.example.hackathon.base.BaseActivity
import com.example.hackathon.presentation.sign_up.SignUpActivity
import com.example.hackathon.util.Constants
import com.example.hackathon.util.PreferenceUtils
import com.example.hackathon.util.state.State
import com.example.hackathon.util.state.StateListener
import com.example.hackathon.util.ui.UIUtil
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
    private lateinit var stateListener: StateListener

    override fun layoutId(): Int {
        return R.layout.activity_sign_in
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setTheme(R.style.LightTheme)
        super.onCreate(savedInstanceState)
        stateListener = this
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
            stateListener.onStateChange(dataState)

            when (dataState) {
                is State.Loading -> {
                    if (dataState.isLoading) {
                        ivButtonProgress.visibility = View.VISIBLE
                        llSignIn.isEnabled = false
                    } else {
                        ivButtonProgress.visibility = View.GONE
                        llSignIn.isEnabled = true
                    }
                }
                is State.Success -> {
                    PreferenceUtils.setAuthorized(this, true)
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
        })
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