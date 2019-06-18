package com.yallam.marvelapp.data.remote

import com.yallam.marvelapp.data.model.CharacterModel
import io.reactivex.Single
import retrofit2.http.GET

/**
 * Created by Yahia Allam on 15/06/2019
 */
interface ApiEndpoints {

    @GET("https://gist.githubusercontent.com/yallam08/b8e80ebcd8786283871860376cd2c733/raw/d17324154aa3a22b484426cdd04797f221b95c05/marvel-characters.json")
    fun getCharacters(): Single<List<CharacterModel>>

    @GET("https://gist.githubusercontent.com/yallam08/9e6beaabeefe6943aa91172b820c3dbb/raw/49d5ff66d4a732fe304fb53d9b0d7005b44d52cf/marvel-characters-more.json")
    fun getMoreCharacters(): Single<List<CharacterModel>>
}