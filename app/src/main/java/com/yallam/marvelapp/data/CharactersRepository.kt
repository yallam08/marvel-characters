package com.yallam.marvelapp.data

import com.yallam.marvelapp.data.remote.ApiEndpoints
import com.yallam.marvelapp.data.remote.model.CharacterModel
import io.reactivex.Single

/**
 * Created by Yahia Allam on 15/06/2019
 */
class CharactersRepository(private val apiEndpoints: ApiEndpoints) {

    fun getCharacters(limit: Long): Single<List<CharacterModel>> {
        return apiEndpoints.getCharacters(limit)
    }
}