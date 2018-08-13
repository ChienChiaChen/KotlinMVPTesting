package com.example.chiachen.ktmvp.models.persistence.daos

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.example.chiachen.ktmvp.models.entities.User

@Dao
interface UserDao {
    @Query("SELECT * FROM user ORDER BY reputation DESC LIMIT (:page - 1) * 30, 30")
    fun getUsers(page: Int): List<User>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(users: List<User>)
}