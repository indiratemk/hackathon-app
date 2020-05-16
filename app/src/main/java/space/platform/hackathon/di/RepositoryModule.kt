package space.platform.hackathon.di

import space.platform.hackathon.data.auth.AuthRemoteDataSource
import space.platform.hackathon.data.hackathon.HackathonRemoteDataSource
import space.platform.hackathon.data.notifications.NotificationsRemoteDataSource
import space.platform.hackathon.data.participants.ParticipantsRemoteDataSource
import space.platform.hackathon.data.teams.TeamRemoteDataSource
import space.platform.hackathon.data.user.UserLocalDataSource
import space.platform.hackathon.data.user.UserRemoteDataSource
import space.platform.hackathon.domain.auth.AuthRepository
import space.platform.hackathon.domain.hackathon.HackathonRepository
import space.platform.hackathon.domain.notifications.NotificationsRepository
import space.platform.hackathon.domain.participants.ParticipantsRepository
import space.platform.hackathon.domain.team.TeamRepository
import space.platform.hackathon.domain.user.UserRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { AuthRepository(get() as AuthRemoteDataSource) }
    single { UserRepository(get() as UserRemoteDataSource, get() as UserLocalDataSource) }
    single { HackathonRepository(get() as HackathonRemoteDataSource) }
    single { ParticipantsRepository(get() as ParticipantsRemoteDataSource) }
    single { TeamRepository(get() as TeamRemoteDataSource) }
    single { NotificationsRepository(get() as NotificationsRemoteDataSource) }
}