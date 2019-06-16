package com.yallam.marvelapp.data.remote

import com.yallam.marvelapp.data.remote.model.CharacterModel
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by Yahia Allam on 15/06/2019
 */
interface ApiEndpoints {

    @GET("https://gist.githubusercontent.com/yallam08/b8e80ebcd8786283871860376cd2c733/raw/d17324154aa3a22b484426cdd04797f221b95c05/marvel-characters.json")
    fun getCharacters(@Query("limit") limit: Long): Single<List<CharacterModel>>
}