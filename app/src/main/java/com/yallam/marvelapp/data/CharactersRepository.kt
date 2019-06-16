package com.yallam.marvelapp.data

import com.yallam.marvelapp.data.local.CharactersDao
import com.yallam.marvelapp.data.model.CharacterModel
import com.yallam.marvelapp.data.remote.ApiEndpoints
import io.reactivex.Single
import java.io.IOException

/**
 * Created by Yahia Allam on 15/06/2019
 */
class CharactersRepository(private val apiEndpoints: ApiEndpoints, private val charactersDao: CharactersDao) {

    fun getCharacters(limit: Long): Single<List<CharacterModel>> {
        return apiEndpoints.getCharacters(limit)
            .doAfterSuccess { character ->
                character.forEach { charactersDao.insertCharacter(it) }
            }.onErrorResumeNext { throwable ->
                return@onErrorResumeNext if (throwable is IOException) {
                    charactersDao.getCharacters().flatMap {
                        return@flatMap if (it.isNotEmpty()) {
                            Single.just(it)
                        } else {
                            Single.error(throwable)
                        }
                    }
                } else {
                    Single.error(throwable)
                }
            }
    }
}