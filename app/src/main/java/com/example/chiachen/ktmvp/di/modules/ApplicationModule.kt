package com.example.chiachen.ktmvp.di.modules

import android.arch.persistence.room.Room
import android.content.Context
import com.example.chiachen.ktmvp.MyApp
import com.example.chiachen.ktmvp.models.persistence.AppDatabase
import com.example.chiachen.ktmvp.models.persistence.daos.UserDao
import com.example.chiachen.ktmvp.models.repositories.DefaultUserRepository
import com.example.chiachen.ktmvp.models.repositories.UserRepository
import com.example.chiachen.ktmvp.models.services.UserService
import com.example.chiachen.ktmvp.utils.*
import com.google.gson.Gson
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class ApplicationModule(val application: MyApp) {
    private val BASE_URL = "https://api.stackexchange.com/2.2/"
    private val DATABASE_NAME = "db-name"

    @Provides
    @Singleton
    fun provideAppContext(): Context = application

    @Provides
    @Singleton
    fun provideConnectionHelper(context: Context) = ConnectionHelper(context)

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .baseUrl(BASE_URL)
            .build()

    @Provides
    @Singleton
    fun provideUserService(retrofit: Retrofit): UserService = retrofit.create(UserService::class.java)

    @Provides
    @Singleton
    fun provideUserRepository(userService: UserService,
                              userDao: UserDao,
                              preferencesHelper: PreferencesHelper,
                              connectionHelper: ConnectionHelper,
                              calendarWrapper: CalendarWrapper
    ): UserRepository {
        return DefaultUserRepository(
                userService,
                userDao,
                preferencesHelper,
                connectionHelper,
                calendarWrapper)
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Context) =
            Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build().userDao()

    @Provides
    @Singleton
    fun provideCalendarWrapper() = CalendarWrapper()

    @Provides
    @Singleton
    fun providePreferences(context: Context) = PreferencesHelper(context)

    @Provides
    @Singleton
    fun provideSchedulerProvider() : SchedulerProvider = AppSchedulerProvider()
}