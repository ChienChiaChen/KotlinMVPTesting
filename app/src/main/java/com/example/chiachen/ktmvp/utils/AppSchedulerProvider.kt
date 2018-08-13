package com.example.chiachen.ktmvp.utils

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AppSchedulerProvider : SchedulerProvider {
    override fun uiScheduler(): Scheduler = AndroidSchedulers.mainThread()
    override fun ioScheduler() = Schedulers.io()
}