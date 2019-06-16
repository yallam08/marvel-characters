package com.yallam.marvelapp.presentation.characterslist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.yallam.marvelapp.base.schedulers.ThreadSchedulers
import com.yallam.marvelapp.data.CharactersRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yahia Allam on 15/06/2019
 */
class CharactersListViewModel(
    private val charactersRepository: CharactersRepository,
    private val threadSchedulers: ThreadSchedulers,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val stateMutableLiveData: MutableLiveData<CharactersListState> = MutableLiveData()

    //TODO: extract to usecase
    fun getCharacters() {
        compositeDisposable.add(
            charactersRepository.getCharacters(20)
                .subscribeOn(threadSchedulers.getIOThread())
                .observeOn(threadSchedulers.getMainThread())
                .doOnSubscribe { stateMutableLiveData.value = CharactersListState.Loading }
                .subscribe(
                    { stateMutableLiveData.value = CharactersListState.Ready(it) },
                    { stateMutableLiveData.value = CharactersListState.Error(it) }
                )
        )
    }

    fun observeState(owner: LifecycleOwner, observer: Observer<CharactersListState>) {
        stateMutableLiveData.observe(owner, observer)
    }

    override fun onCleared() {
        if (compositeDisposable.isDisposed.not()) compositeDisposable.dispose()
        super.onCleared()
    }

}