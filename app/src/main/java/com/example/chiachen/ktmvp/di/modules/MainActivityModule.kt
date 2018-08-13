package com.example.chiachen.ktmvp.di.modules

import com.example.chiachen.ktmvp.domain.interactors.GetUsers
import com.example.chiachen.ktmvp.models.repositories.UserRepository
import com.example.chiachen.ktmvp.presentation.presenters.UserListPresenter
import com.example.chiachen.ktmvp.utils.SchedulerProvider
import dagger.Module
import dagger.Provides

@Module
class MainActivityModule() {

    @Provides
    fun provideGetUsers(userRepository: UserRepository) = GetUsers(userRepository)

    @Provides
    fun providePresenter(getUsers: GetUsers, schedulerProvider: SchedulerProvider) = UserListPresenter(getUsers, schedulerProvider)
}