package com.example.chiachen.ktmvp.presentation.view.activities

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.chiachen.ktmvp.R

class MainActivity : AppCompatActivity(), UserListView {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun showLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideLoading() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showEmptyListError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun hideEmptyListError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun showToastError() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun clearList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}
