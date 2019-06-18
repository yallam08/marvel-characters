package com.yallam.marvelapp.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.nhaarman.mockitokotlin2.whenever
import com.yallam.marvelapp.data.local.CharactersDao
import com.yallam.marvelapp.data.model.CharacterModel
import com.yallam.marvelapp.data.remote.ApiEndpoints
import io.reactivex.Single
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import java.io.FileReader

/**
 * Created by Yahia Allam on 18/06/2019
 */
class CharactersRepositoryTest {

    private lateinit var charactersRepository: ICharactersRepository
    @Mock
    lateinit var apiEndpoints: ApiEndpoints
    @Mock
    lateinit var charactersDao: CharactersDao

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        charactersRepository = CharactersRepository(apiEndpoints, charactersDao)
    }

    @Test
    fun getCharactersTest() {
        val characters: List<CharacterModel> = getFakeCharactersList()
        whenever(apiEndpoints.getCharacters()).thenReturn(Single.just(characters))
        whenever(charactersDao.getCharacters()).thenReturn(Single.just(characters))

        val testObserver = charactersRepository.getCharacters().test()
        testObserver.assertResult(characters)
        testObserver.assertComplete()
    }

    private fun getFakeCharactersList(): List<CharacterModel> {
        val type = object : TypeToken<List<CharacterModel>>() {}.type
        return Gson().fromJson(
            JsonReader(FileReader("src/test/java/com/yallam/marvelapp/data/fake-characters.json")),
            type
        )
    }

}