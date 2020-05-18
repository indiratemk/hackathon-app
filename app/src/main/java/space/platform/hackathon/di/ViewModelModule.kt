package space.platform.hackathon.di

import space.platform.hackathon.domain.auth.AuthRepository
import space.platform.hackathon.domain.hackathon.HackathonRepository
import space.platform.hackathon.domain.notifications.NotificationsRepository
import space.platform.hackathon.domain.participants.ParticipantsRepository
import space.platform.hackathon.domain.team.TeamRepository
import space.platform.hackathon.domain.user.UserRepository
import space.platform.hackathon.presentation.hackathon.detail.HackathonDetailViewModel
import space.platform.hackathon.presentation.hackathon.participants.ParticipantsViewModel
import space.platform.hackathon.presentation.hackathon.registration.HackathonRegistrationViewModel
import space.platform.hackathon.presentation.hackathon.teams.TeamsViewModel
import space.platform.hackathon.presentation.hackathons.HackathonsViewModel
import space.platform.hackathon.presentation.logout.LogoutViewModel
import space.platform.hackathon.presentation.notifications.NotificationsViewModel
import space.platform.hackathon.presentation.profile.ProfileViewModel
import space.platform.hackathon.presentation.qr_scanner.QRScannerViewModel
import space.platform.hackathon.presentation.sign_in.SignInViewModel
import space.platform.hackathon.presentation.sign_up.SignUpViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module
import space.platform.hackathon.domain.tasks.TasksRepository
import space.platform.hackathon.presentation.tasks.TasksViewModel

val viewModelModule = module {
    viewModel { SignUpViewModel(get() as AuthRepository) }
    viewModel { SignInViewModel(get() as AuthRepository) }
    viewModel { LogoutViewModel(get() as AuthRepository) }
    viewModel { ProfileViewModel(get() as UserRepository, get() as ParticipantsRepository, get() as NotificationsRepository) }
    viewModel { HackathonsViewModel(get() as HackathonRepository) }
    viewModel { HackathonDetailViewModel(get() as HackathonRepository, get() as ParticipantsRepository) }
    viewModel { HackathonRegistrationViewModel(get() as ParticipantsRepository) }
    viewModel { QRScannerViewModel(get() as ParticipantsRepository) }
    viewModel { ParticipantsViewModel(get() as HackathonRepository, get() as UserRepository, get() as TeamRepository) }
    viewModel { TeamsViewModel(get() as HackathonRepository, get() as ParticipantsRepository, get() as TeamRepository) }
    viewModel { NotificationsViewModel(get() as NotificationsRepository, get() as TeamRepository) }
    viewModel { TasksViewModel(get() as TasksRepository) }
}