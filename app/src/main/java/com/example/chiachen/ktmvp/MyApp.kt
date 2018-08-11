package com.example.chiachen.ktmvp

import android.app.Application
import com.example.chiachen.ktmvp.di.components.ApplicationComponent
import com.example.chiachen.ktmvp.di.components.DaggerApplicationComponent
import com.example.chiachen.ktmvp.di.modules.ApplicationModule
import com.facebook.stetho.Stetho

class MyApp : Application() {
    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
        Stetho.initializeWithDefaults(this)
        component.inject(this)
    }

    private fun initAppComponent() {
        component = DaggerApplicationComponent
                .builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }
}
