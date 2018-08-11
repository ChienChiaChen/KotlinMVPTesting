package com.example.chiachen.ktmvp.di.modules

import android.content.Context
import com.example.chiachen.ktmvp.MyApp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(val application: MyApp) {
    @Provides
    @Singleton
    fun provideAppContext() : Context = application


}