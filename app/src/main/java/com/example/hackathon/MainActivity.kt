package com.example.hackathon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.hackathon.presentation.SignUpViewModel
import com.example.hackathon.util.State
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {

    private val signUpViewModel: SignUpViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signUpViewModel.user.observe(this, Observer {
            when (it) {
                is State.Loading -> Log.d("testtesttest","loading")
                is State.Success -> Log.d("testtesttest","success, data: ${it.data}")
                is State.NetworkError -> Log.d("testtesttest","error, message: ${it.message}")
            }
        })

        btnClick.setOnClickListener { signUpViewModel.signUp("indiratemk", "indiratemk@gmail.com", "12345678") }
    }
}
