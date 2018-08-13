package com.example.chiachen.ktmvp.presentation.view.activities

import com.example.chiachen.ktmvp.presentation.view.viewmodels.UserViewModel

interface UserListView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyListError()
    fun hideEmptyListError()
    fun showToastError()
    fun clearList()
    fun addUsersToList(users: List<UserViewModel>)
}