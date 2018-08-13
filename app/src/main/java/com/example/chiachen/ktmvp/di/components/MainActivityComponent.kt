package com.example.chiachen.ktmvp.di.components

import com.example.chiachen.ktmvp.di.modules.MainActivityModule
import com.example.chiachen.ktmvp.presentation.presenters.UserListPresenter
import dagger.Subcomponent

@Subcomponent(modules = arrayOf(MainActivityModule::class))
interface MainActivityComponent {
    fun presenter(): UserListPresenter
}