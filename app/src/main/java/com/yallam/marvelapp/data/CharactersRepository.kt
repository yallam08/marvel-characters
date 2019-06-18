package com.yallam.marvelapp.data

import com.yallam.marvelapp.data.local.CharactersDao
import com.yallam.marvelapp.data.model.CharacterModel
import com.yallam.marvelapp.data.remote.ApiEndpoints
import io.reactivex.Single
import java.io.IOException

/**
 * Created by Yahia Allam on 15/06/2019
 * //TODO add additional *data source* layer to the repository, i.e (RemoteDataSource, LocalDataSource) interfaces
 */
class CharactersRepository(private val apiEndpoints: ApiEndpoints, private val charactersDao: CharactersDao) :
    ICharactersRepository {

    override fun getCharacters(): Single<List<CharacterModel>> {
        return apiEndpoints.getCharacters()
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

    override fun getMoreCharacters(): Single<List<CharacterModel>> {
        return apiEndpoints.getMoreCharacters()
            .doAfterSuccess { character ->
                character.forEach { charactersDao.insertCharacter(it) }
            }
    }

    override fun getCharacterById(characterId: Long): Single<CharacterModel> {
        return charactersDao.getCharacterById(characterId)
    }
}