package com.example.chiachen.ktmvp.domain.interactors

import com.example.chiachen.ktmvp.models.entities.UserListModel
import com.example.chiachen.ktmvp.models.repositories.UserRepository
import com.example.chiachen.ktmvp.presentation.view.viewmodels.UserViewModel
import io.reactivex.Single

class GetUsers(private val userRepository: UserRepository) {
    fun execute(page: Int, forced: Boolean): Single<List<UserViewModel>> {
        val userList = userRepository.getUsers(page, forced)
        return userList.map { userListModel: UserListModel? ->
            val items = userListModel?.items ?: emptyList()
            items.map { UserViewModel(it.userId, it.displayName, it.reputation, it.profileImage) }
        }
    }
}