package com.example.chiachen.ktmvp.utils

import io.reactivex.Scheduler

interface SchedulerProvider {
    fun uiScheduler() : Scheduler
    fun ioScheduler() : Scheduler
}