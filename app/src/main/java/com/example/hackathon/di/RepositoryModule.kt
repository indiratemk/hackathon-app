package com.example.hackathon.di

import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.hackathon.HackathonRemoteDataSource
import com.example.hackathon.data.participants.ParticipantsRemoteDataSource
import com.example.hackathon.data.user.UserLocalDataSource
import com.example.hackathon.data.user.UserRemoteDataStore
import com.example.hackathon.domain.auth.AuthRepository
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.domain.participants.ParticipantsRepository
import com.example.hackathon.domain.user.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get() as AuthRemoteDataSource) }
    single { UserRepository(get() as UserRemoteDataStore, get() as UserLocalDataSource) }
    single { HackathonRepository(get() as HackathonRemoteDataSource) }
    single { ParticipantsRepository(get() as ParticipantsRemoteDataSource) }
}