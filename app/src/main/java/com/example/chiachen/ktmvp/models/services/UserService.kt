package com.example.chiachen.ktmvp.models.services

import com.example.chiachen.ktmvp.models.entities.UserListModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface UserService {
    @GET("/users?order=desc&sort=reputation&site=stackoverflow")
    fun getUsers(@Query("page") page: Int = 1) :Call<UserListModel>
}