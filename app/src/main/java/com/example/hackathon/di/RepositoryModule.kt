package com.example.hackathon.di

import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.user.UserRemoteDataStore
import com.example.hackathon.domain.auth.AuthRepository
import com.example.hackathon.domain.user.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get() as AuthRemoteDataSource) }
    single { UserRepository(get() as UserRemoteDataStore) }
}