package com.example.chiachen.ktmvp.di.components

import com.example.chiachen.ktmvp.MyApp
import com.example.chiachen.ktmvp.di.modules.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {
    fun inject(application: MyApp)
}