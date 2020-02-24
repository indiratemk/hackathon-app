package com.example.hackathon.presentation.sign_in

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.hackathon.R
import com.example.hackathon.presentation.sign_up.SignUpActivity
import com.example.hackathon.util.State
import com.example.hackathon.util.ui.UIUtil
import kotlinx.android.synthetic.main.activity_sign_in.*
import org.koin.android.viewmodel.ext.android.viewModel

class SignInActivity : AppCompatActivity() {

    companion object {
        fun startActivity(context: Context) {
            val intent = Intent(context, SignInActivity::class.java)
            context.startActivity(intent)
        }
    }

    private val signInViewModel: SignInViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)
        initUI()
    }

//    TODO сделать нормальную обработку ошибок!!!
    private fun initUI() {
        observeUser()

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
            SignUpActivity.startActivity(this)
        }

        ivClose.setOnClickListener { finish() }

    }

    private fun observeUser() {
        signInViewModel.user.observe(this, Observer {
            when (it) {
                is State.Loading -> {
                    if (it.isLoading) {
                        ivButtonProgress.visibility = View.VISIBLE
                        llSignIn.isEnabled = false
                    } else {
                        ivButtonProgress.visibility = View.GONE
                        llSignIn.isEnabled = true
                    }
                }
                is State.Success -> {
                    setResult(Activity.RESULT_OK)
                    finish()
                }
                is State.NetworkError -> {
                    UIUtil.showErrorMessage(this, it.message)
                }
                is State.BackendError -> {
                    UIUtil.showErrorMessage(this, it.message)
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