package com.example.chiachen.ktmvp.models.repositories

import com.example.chiachen.ktmvp.models.entities.UserListModel
import io.reactivex.Single

interface UserRepository {
    fun getUsers(page: Int = 1, forced: Boolean = false): Single<UserListModel>
}