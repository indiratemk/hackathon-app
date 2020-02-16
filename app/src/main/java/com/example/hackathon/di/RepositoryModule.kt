package com.example.hackathon.di

import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.domain.AuthRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get() as AuthRemoteDataSource) }
}