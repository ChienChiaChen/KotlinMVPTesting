package com.example.chiachen.ktmvp.models.repositories

import com.example.chiachen.ktmvp.models.entities.UserListModel
import com.example.chiachen.ktmvp.models.persistence.daos.UserDao
import com.example.chiachen.ktmvp.models.services.UserService
import com.example.chiachen.ktmvp.utils.CalendarWrapper
import com.example.chiachen.ktmvp.utils.ConnectionHelper
import com.example.chiachen.ktmvp.utils.Constants
import com.example.chiachen.ktmvp.utils.PreferencesHelper
import io.reactivex.Single
import io.reactivex.SingleEmitter

class DefaultUserRepository(private val userService: UserService,
                            private val userDao: UserDao,
                            private val preferencesHelper: PreferencesHelper,
                            private val connectionHelper: ConnectionHelper,
                            private val calendarWrapper: CalendarWrapper) : UserRepository {

    private val LAST_UPDATE_KEY = "last_update_page_"

    override fun getUsers(page: Int, forced: Boolean): Single<UserListModel> {
        return Single.create<UserListModel> {
            emitter: SingleEmitter<UserListModel> ->
                if (shouldUpdate(page, forced)) {
                    loadUserFromNetwork(page, emitter)
                } else {
                    loadOfflineUsers(page, emitter)
                }
        }
    }

    private fun shouldUpdate(page: Int, forced: Boolean) = when {
        forced -> true
        !connectionHelper.isOnline() -> false
        else -> {
            val lastUpdate = preferencesHelper.loadLong(LAST_UPDATE_KEY + page)
            val currentTime = calendarWrapper.getCurrentTimeInMillis()
            lastUpdate + Constants.REFRESH_LIMIT < currentTime
        }
    }

    private fun loadOfflineUsers(page: Int, emitter: SingleEmitter<UserListModel>) {
        val users = userDao.getUsers(page)
        if (!users.isEmpty()) {
            emitter.onSuccess(UserListModel(users))
        } else {
            emitter.onError(Exception("Device is offline"))
        }
    }

    private fun loadUserFromNetwork(page: Int, emitter: SingleEmitter<UserListModel>) {
        val users = userService.getUsers(page).execute().body()
        if (null != users) {
            userDao.insertAll(users.items)
            val currentTime = calendarWrapper.getCurrentTimeInMillis()
            preferencesHelper.saveLong(LAST_UPDATE_KEY + page, currentTime)
            emitter.onSuccess(users)
        } else {
            emitter.onError(Exception("No data received"))
        }
    }
}
