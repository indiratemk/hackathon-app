package com.example.hackathon.di

import com.example.hackathon.domain.AuthRepository
import com.example.hackathon.presentation.SignUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get() as AuthRepository) }
}