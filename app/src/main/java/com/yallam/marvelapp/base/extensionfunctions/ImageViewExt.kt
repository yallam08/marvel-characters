package com.yallam.marvelapp.base.extensionfunctions

import android.widget.ImageView
import com.yallam.marvelapp.R
import com.yallam.marvelapp.base.GlideApp

/**
 * Created by Yahia Allam on 16/06/2019
 */
fun ImageView.load(resId: Int) {
    GlideApp.with(this)
        .load(resId)
        .error(R.drawable.characters_list_placeholder)
        .into(this)
}