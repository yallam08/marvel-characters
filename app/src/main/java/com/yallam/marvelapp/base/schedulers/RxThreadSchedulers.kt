package com.yallam.marvelapp.base.schedulers

import io.reactivex.Scheduler
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by Yahia Allam on 16/06/2019
 */
class RxThreadSchedulers : ThreadSchedulers {

    override fun getIOThread(): Scheduler = Schedulers.io()

    override fun getMainThread(): Scheduler = AndroidSchedulers.mainThread()
}