package com.yallam.marvelapp.base.extensionfunctions

import android.content.Context

/**
 * Created by Yahia Allam on 18/06/2019
 */
fun Context.getDrawableResIdFromName(drawableName: String): Int {
    return resources.getIdentifier(drawableName, "drawable", packageName)
}