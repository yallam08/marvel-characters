package com.yallam.marvelapp.presentation.characterslist

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.yallam.marvelapp.base.schedulers.ThreadSchedulers
import com.yallam.marvelapp.data.ICharactersRepository
import io.reactivex.disposables.CompositeDisposable

/**
 * Created by Yahia Allam on 15/06/2019
 */
class CharactersListViewModel(
    private val charactersRepository: ICharactersRepository,
    private val threadSchedulers: ThreadSchedulers,
    private val compositeDisposable: CompositeDisposable
) : ViewModel() {

    private val initialStateMutableLiveData: MutableLiveData<CharactersListState> = MutableLiveData()
    private val loadMoreStateMutableLiveData: MutableLiveData<CharactersListState> = MutableLiveData()

    //TODO: extract to usecase
    fun getCharacters() {
        compositeDisposable.add(
            charactersRepository.getCharacters()
                .subscribeOn(threadSchedulers.getIOThread())
                .doOnSubscribe { initialStateMutableLiveData.postValue(CharactersListState.Loading) }
                .subscribe(
                    { initialStateMutableLiveData.postValue(CharactersListState.Ready(it)) },
                    { initialStateMutableLiveData.postValue(CharactersListState.Error(it)) }
                )
        )
    }

    fun getMoreCharacters() {
        compositeDisposable.add(
            charactersRepository.getMoreCharacters()
                .subscribeOn(threadSchedulers.getIOThread())
                .doOnSubscribe { loadMoreStateMutableLiveData.postValue(CharactersListState.Loading) }
                .subscribe(
                    { loadMoreStateMutableLiveData.postValue(CharactersListState.Ready(it)) },
                    { loadMoreStateMutableLiveData.postValue(CharactersListState.Error(it)) }
                )
        )
    }

    fun observeInitialState(owner: LifecycleOwner, observer: Observer<CharactersListState>) {
        initialStateMutableLiveData.observe(owner, observer)
    }

    fun observeLoadMoreState(owner: LifecycleOwner, observer: Observer<CharactersListState>) {
        loadMoreStateMutableLiveData.observe(owner, observer)
    }

    override fun onCleared() {
        if (compositeDisposable.isDisposed.not()) compositeDisposable.dispose()
        super.onCleared()
    }

}