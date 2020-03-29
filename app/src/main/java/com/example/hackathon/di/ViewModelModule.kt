package com.example.hackathon.di

import com.example.hackathon.domain.auth.AuthRepository
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.domain.participants.ParticipantsRepository
import com.example.hackathon.domain.user.UserRepository
import com.example.hackathon.presentation.hackathon.detail.HackathonDetailViewModel
import com.example.hackathon.presentation.hackathon.registration.HackathonRegistrationViewModel
import com.example.hackathon.presentation.hackathons.HackathonsViewModel
import com.example.hackathon.presentation.logout.LogoutViewModel
import com.example.hackathon.presentation.profile.ProfileViewModel
import com.example.hackathon.presentation.qr_scanner.QRScannerViewModel
import com.example.hackathon.presentation.sign_in.SignInViewModel
import com.example.hackathon.presentation.sign_up.SignUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { SignUpViewModel(get() as AuthRepository) }
    viewModel { SignInViewModel(get() as AuthRepository) }
    viewModel { LogoutViewModel(get() as AuthRepository) }
    viewModel { ProfileViewModel(get() as UserRepository) }
    viewModel { HackathonsViewModel(get() as HackathonRepository) }
    viewModel { HackathonDetailViewModel(get() as HackathonRepository, get() as ParticipantsRepository) }
    viewModel { HackathonRegistrationViewModel(get() as ParticipantsRepository) }
    viewModel { QRScannerViewModel(get() as ParticipantsRepository) }
}