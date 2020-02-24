package com.example.hackathon

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hackathon.presentation.sign_in.SignInActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnClick.setOnClickListener { SignInActivity.startActivity(this) }
    }
}
