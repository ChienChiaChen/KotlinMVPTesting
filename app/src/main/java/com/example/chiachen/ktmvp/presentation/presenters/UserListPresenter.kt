package com.example.chiachen.ktmvp.presentation.presenters

import com.example.chiachen.ktmvp.domain.interactors.GetUsers
import com.example.chiachen.ktmvp.models.entities.UserListModel
import com.example.chiachen.ktmvp.presentation.view.activities.UserListView
import com.example.chiachen.ktmvp.presentation.view.viewmodels.UserViewModel
import com.example.chiachen.ktmvp.utils.SchedulerProvider


class UserListPresenter(val getUsers: GetUsers,
                        private val schedulerProvider: SchedulerProvider) : BasePresenter<UserListView>() {
    private val offset = 5
    private var page = 1
    private var loading = false

    fun getUsers(forced: Boolean = false) {
        loading = true
        val pageToRequest = if (forced) 1 else page
        getUsers.execute(pageToRequest, forced)
                .subscribeOn(schedulerProvider.ioScheduler())
                .observeOn(schedulerProvider.uiScheduler())
                .subscribe(
                        { users -> handleSuccess(forced, users) },
                        { handleError() })
    }

    private fun handleError() {
        loading = false
        view?.hideLoading()
        if (page == 1) {
            view?.showEmptyListError()
        } else {
            view?.showToastError()
        }
    }

    private fun handleSuccess(forced: Boolean, users: List<UserViewModel>) {
        loading = false
        if (forced) {
            page = 1
        }

        if (page == 1) {
            view?.clearList()
            view?.hideEmptyListError()
        }

        view?.addUsersToList(users)
        view?.hideLoading()
        page++
    }

    fun onScrollChanged(lastVisibleItemPosition: Int, totalItemCount: Int) {
        val shouldGetNextPage = !loading && lastVisibleItemPosition >= totalItemCount - offset
        if (shouldGetNextPage) {
            getUsers()
        }

        if (loading && lastVisibleItemPosition >= totalItemCount) {
            view?.showLoading()
        }
    }

}