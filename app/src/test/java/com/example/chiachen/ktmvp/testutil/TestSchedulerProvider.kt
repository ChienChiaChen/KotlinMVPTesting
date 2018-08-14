package com.example.chiachen.ktmvp.testutil

import com.example.chiachen.ktmvp.utils.SchedulerProvider
import io.reactivex.schedulers.TestScheduler

class TestSchedulerProvider : SchedulerProvider {
    val testScheduler: TestScheduler = TestScheduler()

    override fun uiScheduler() = testScheduler
    override fun ioScheduler() = testScheduler
}