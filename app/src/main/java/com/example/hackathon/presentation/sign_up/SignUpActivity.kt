package com.example.hackathon.presentation.sign_up

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.lifecycle.Observer
import com.example.hackathon.R
import com.example.hackathon.base.BaseActivity
import com.example.hackathon.presentation.sign_in.SignInActivity
import com.example.hackathon.util.state.State
import com.example.hackathon.util.state.StateListener
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_sign_up.*
import org.koin.android.viewmodel.ext.android.viewModel

class SignUpActivity : BaseActivity(), StateListener {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SignUpActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val signUpViewModel: SignUpViewModel by viewModel()
    private lateinit var stateListener: StateListener

    override fun layoutId(): Int {
        return R.layout.activity_sign_up
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        stateListener = this
        subscribeObservers()
        initUI()
    }

    private fun initUI() {
        llSignUp.setOnClickListener {
            validateLogin()
            validateEmail()
            validatePassword()
            if (tilEmail.error == null && tilPassword.error == null) {
                signUpViewModel.signUp(tietLogin.text.toString().trim(),
                    tietEmail.text.toString().trim(),
                    tietPassword.text.toString().trim())
                UIUtil.hideKeyboard(this)
            }
        }

        tvSignIn.setOnClickListener {
            finish()
            SignInActivity.startActivity(this)
        }

        ivClose.setOnClickListener { finish() }
    }

    private fun subscribeObservers() {
        signUpViewModel.user.observe(this, Observer { dataState ->
            when (dataState) {
                is State.Loading -> {
                    if (dataState.isLoading) {
                        ivButtonProgress.visibility = View.VISIBLE
                        llSignUp.isEnabled = false
                    } else {
                        ivButtonProgress.visibility = View.GONE
                        llSignUp.isEnabled = true
                    }
                }
                is State.Success -> {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
            }
            stateListener.onStateChange(dataState)
        })
    }

    private fun validateLogin() {
        val login = tietLogin.text.toString().trim()
        if (login.isEmpty()) {
            tilLogin.isErrorEnabled = true
            tilLogin.error = getString(R.string.registration_empty_login)
        } else {
            tilLogin.isErrorEnabled = false
            tilLogin.error = null
        }
    }

    private fun validateEmail() {
        val email = tietEmail.text.toString().trim()
        if (email.isEmpty()) {
            tilEmail.isErrorEnabled = true
            tilEmail.error = getString(R.string.registration_empty_email_error)
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            tilEmail.isErrorEnabled = true
            tilEmail.error = getString(R.string.registration_invalid_email_error)
        } else {
            tilEmail.isErrorEnabled = false
            tilEmail.error = null
        }
    }

    private fun validatePassword() {
        val password = tietPassword.text.toString().trim()
        if (password.isEmpty()) {
            tilPassword.isErrorEnabled = true
            tilPassword.error = getString(R.string.registration_empty_password)
        } else {
            tilPassword.isErrorEnabled = false
            tilPassword.error = null
        }
    }
}