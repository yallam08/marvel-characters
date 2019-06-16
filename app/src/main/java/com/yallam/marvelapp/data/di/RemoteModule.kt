package com.yallam.marvelapp.data.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.yallam.marvelapp.data.remote.ApiEndpoints
import com.yallam.marvelapp.data.remote.NetworkConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Yahia Allam on 15/06/2019
 */
val remoteModule = module {

    single<Gson> { GsonBuilder().serializeNulls().create() }

    single<OkHttpClient> {
        OkHttpClient.Builder()
//            .addInterceptor(ApiParamsInterceptor())
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .build()
    }

    single<Retrofit> {
        Retrofit.Builder()
            .client(get())
            .addConverterFactory(GsonConverterFactory.create(get()))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .baseUrl(NetworkConstants.MARVEL_API_BASE_URL)
            .build()
    }

    single<ApiEndpoints> {
        get<Retrofit>().create(ApiEndpoints::class.java)
    }

}