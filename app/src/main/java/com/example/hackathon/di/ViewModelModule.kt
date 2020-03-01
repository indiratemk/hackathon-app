package com.example.hackathon.di

import com.example.hackathon.domain.auth.AuthRepository
import com.example.hackathon.domain.user.UserRepository
import com.example.hackathon.presentation.profile.ProfileViewModel
import com.example.hackathon.presentation.sign_up.SignUpViewModel
import com.example.hackathon.presentation.sign_in.SignInViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get() as AuthRepository) }
    viewModel { SignInViewModel(get() as AuthRepository) }
    viewModel { ProfileViewModel(get() as UserRepository) }
}