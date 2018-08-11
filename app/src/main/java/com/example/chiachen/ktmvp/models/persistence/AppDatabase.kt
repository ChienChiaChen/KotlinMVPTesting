package com.example.chiachen.ktmvp.models.persistence

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import com.example.chiachen.ktmvp.models.entities.User
import com.example.chiachen.ktmvp.models.persistence.daos.UserDao

@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}