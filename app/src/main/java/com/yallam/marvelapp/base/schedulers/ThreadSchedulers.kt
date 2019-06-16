package com.yallam.marvelapp.base.schedulers

import io.reactivex.Scheduler

/**
 * Created by Yahia Allam on 16/06/2019
 */
interface ThreadSchedulers {

    fun getIOThread(): Scheduler
    fun getMainThread(): Scheduler
}