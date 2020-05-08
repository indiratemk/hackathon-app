package com.example.hackathon.di

import com.example.hackathon.data.auth.AuthRemoteDataSource
import com.example.hackathon.data.hackathon.HackathonRemoteDataSource
import com.example.hackathon.data.notifications.NotificationsRemoteDataSource
import com.example.hackathon.data.participants.ParticipantsRemoteDataSource
import com.example.hackathon.data.teams.TeamRemoteDataSource
import com.example.hackathon.data.user.UserLocalDataSource
import com.example.hackathon.data.user.UserRemoteDataSource
import com.example.hackathon.domain.auth.AuthRepository
import com.example.hackathon.domain.hackathon.HackathonRepository
import com.example.hackathon.domain.notifications.NotificationsRepository
import com.example.hackathon.domain.participants.ParticipantsRepository
import com.example.hackathon.domain.team.TeamRepository
import com.example.hackathon.domain.user.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get() as AuthRemoteDataSource) }
    single { UserRepository(get() as UserRemoteDataSource, get() as UserLocalDataSource) }
    single { HackathonRepository(get() as HackathonRemoteDataSource) }
    single { ParticipantsRepository(get() as ParticipantsRemoteDataSource) }
    single { TeamRepository(get() as TeamRemoteDataSource) }
    single { NotificationsRepository(get() as NotificationsRemoteDataSource) }
}