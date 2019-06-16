package com.yallam.marvelapp.data.remote

import com.yallam.marvelapp.BuildConfig

/**
 * Created by Yahia Allam on 15/06/2019
 */
object NetworkConstants {

    const val MARVEL_API_BASE_URL = "https://gateway.marvel.com/v1/public/"

    const val AUTH_PARAM_KEY = "apikey"
    const val AUTH_PUBLIC_API_KEY = BuildConfig.MARVEL_PUBLIC_API_KEY
    const val AUTH_PRIVATE_API_KEY = BuildConfig.MARVEL_PRIVATE_API_KEY

    const val TIMESTAMP_PARAM_KEY = "ts"
    const val HASH_PARAM_KEY = "hash"
}