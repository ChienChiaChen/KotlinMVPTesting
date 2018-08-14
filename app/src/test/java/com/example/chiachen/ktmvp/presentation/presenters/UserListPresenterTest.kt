package com.example.chiachen.ktmvp.presentation.presenters

import com.example.chiachen.ktmvp.domain.interactors.GetUsers
import com.example.chiachen.ktmvp.presentation.view.activities.UserListView
import com.example.chiachen.ktmvp.presentation.view.viewmodels.UserViewModel
import com.example.chiachen.ktmvp.testutil.TestSchedulerProvider
import io.reactivex.Single
import org.junit.Test

import org.junit.Before
import org.mockito.ArgumentMatchers.anyBoolean
import org.mockito.ArgumentMatchers.anyInt
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.mockito.MockitoAnnotations
import java.util.concurrent.TimeUnit
import org.mockito.Mockito.`when` as whenever

class UserListPresenterTest {

    @Mock
    lateinit var mockGetUsers: GetUsers

    @Mock
    lateinit var mockView: UserListView

    lateinit var userListPresenter: UserListPresenter

    lateinit var testSchedulerProvider: TestSchedulerProvider

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        testSchedulerProvider = TestSchedulerProvider()
        userListPresenter = UserListPresenter(mockGetUsers, testSchedulerProvider)
    }

    @Test
    fun testGetUsers_errorCase_showError() {
        // Given
        val error = "Test error"
        val single: Single<List<UserViewModel>> = Single.create {
            emitter ->
            emitter.onError(Exception(error))
        }

        // When
        whenever(mockGetUsers.execute(anyInt(), anyBoolean())).thenReturn(single)

        userListPresenter.attach(mockView)
        userListPresenter.getUsers()

        testSchedulerProvider.testScheduler.triggerActions()

        // Then
        verify(mockView).hideLoading()
        verify(mockView).showEmptyListError()
    }

    @Test
    fun testGetUsers_successCaseFirstPage_clearList() {
        val users = listOf(UserViewModel(1, "Name", 1000, ""))
        val single: Single<List<UserViewModel>> = Single.create {
            emitter ->
            emitter.onSuccess(users)
        }

        whenever(mockGetUsers.execute(anyInt(), anyBoolean())).thenReturn(single)
        userListPresenter.attach(mockView)
        userListPresenter.getUsers()
        testSchedulerProvider.testScheduler.triggerActions()

        verify(mockView).clearList()
    }

    @Test
    fun testGetUsers_successCaseMultipleTimes_clearListOnlyOnce() {
        // Given
        val users = listOf(UserViewModel(1, "Name", 1000, ""))
        val single: Single<List<UserViewModel>> = Single.create {
            emitter ->
            emitter.onSuccess(users)
        }

        // When
        whenever(mockGetUsers.execute(anyInt(), anyBoolean())).thenReturn(single)
        userListPresenter.attach(mockView)
        userListPresenter.getUsers()
        userListPresenter.getUsers()
        testSchedulerProvider.testScheduler.triggerActions()

        // Then
        verify(mockView).clearList()
        verify(mockView, times(2)).hideLoading()
        verify(mockView, times(2)).addUsersToList(users)
    }

    @Test
    fun testGetUsers_forcedSuccessCaseMultipleTimes_clearListEveryTime() {
        // Given
        val users = listOf(UserViewModel(1, "Name", 1000, ""))
        val single: Single<List<UserViewModel>> = Single.create {
            emitter ->
            emitter.onSuccess(users)
        }

        // When
        whenever(mockGetUsers.execute(anyInt(), anyBoolean())).thenReturn(single)
        userListPresenter.attach(mockView)
        userListPresenter.getUsers(forced = true)
        userListPresenter.getUsers(forced = true)
        testSchedulerProvider.testScheduler.advanceTimeBy(1, TimeUnit.SECONDS)
        // Then
        verify(mockView, times(2)).clearList()
        verify(mockView, times(2)).hideLoading()
        verify(mockView, times(2)).addUsersToList(users)
    }
}