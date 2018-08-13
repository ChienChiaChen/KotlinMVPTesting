package com.example.chiachen.ktmvp.presentation.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Toast
import com.example.chiachen.ktmvp.R
import com.example.chiachen.ktmvp.di.modules.MainActivityModule
import com.example.chiachen.ktmvp.presentation.presenters.UserListPresenter
import com.example.chiachen.ktmvp.presentation.view.adapters.UserListAdapter
import com.example.chiachen.ktmvp.presentation.view.viewmodels.UserViewModel
import com.example.chiachen.ktmvp.utils.myApp
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), UserListView {

    private val component by lazy { myApp.component.inject(MainActivityModule()) }
    private val presenter: UserListPresenter by lazy { component.presenter() }
    private lateinit var layoutManager: LinearLayoutManager

    private val adapter by lazy {
        val userList = mutableListOf<UserViewModel>()
        UserListAdapter(userList) { user ->
            showUserClickedSnackbar(user)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        initAdapter()
        presenter.attach(this)
        showLoading()
        presenter.getUsers()
    }

    private fun initViews() {
        swipeRefreshLayout.setOnRefreshListener {
            presenter.getUsers(forced = true)
        }
    }

    private fun initAdapter() {
        layoutManager = LinearLayoutManager(myApp)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                val lastVisibleItemPosition = layoutManager.findFirstVisibleItemPosition() + layoutManager.childCount
                val totalItemCount = layoutManager.itemCount
                presenter.onScrollChanged(lastVisibleItemPosition, totalItemCount)
            }
        })
    }

    override fun addUsersToList(users: List<UserViewModel>) {
        val adapter = recyclerView.adapter as UserListAdapter
        adapter.addUsers(users)
    }

    override fun showLoading() {
        swipeRefreshLayout.isRefreshing = true
    }

    override fun hideLoading() {
        swipeRefreshLayout.isRefreshing = false
    }

    override fun showEmptyListError() {
        errorView.visibility = View.VISIBLE
    }

    override fun hideEmptyListError() {
        errorView.visibility = View.GONE
    }

    override fun showToastError() {
        Toast.makeText(this, "Error loading data", Toast.LENGTH_SHORT).show()
    }

    override fun clearList() {
        adapter.clearUsers()
    }

    private fun showUserClickedSnackbar(user: UserViewModel) {
        Snackbar.make(recyclerView, "${user.displayName}: ${user.reputation} pts", Snackbar.LENGTH_SHORT).show()
    }

}
