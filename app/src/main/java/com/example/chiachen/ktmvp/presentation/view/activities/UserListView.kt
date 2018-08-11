package com.example.chiachen.ktmvp.presentation.view.activities

interface UserListView {
    fun showLoading()
    fun hideLoading()
    fun showEmptyListError()
    fun hideEmptyListError()
    fun showToastError()
    fun clearList()
}