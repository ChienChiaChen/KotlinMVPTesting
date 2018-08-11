package com.example.chiachen.ktmvp.presentation.presenters

abstract class BasePresenter<T> {
    var view: T? = null
        private set

    fun attach(view: T) {
        this.view = view
    }

    fun detach() {
        this.view = null
    }

    val isViewAttached: Boolean
        get() = view != null

}