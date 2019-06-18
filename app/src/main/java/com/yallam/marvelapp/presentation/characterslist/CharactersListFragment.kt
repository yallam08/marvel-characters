package com.yallam.marvelapp.presentation.characterslist


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.yallam.marvelapp.R
import com.yallam.marvelapp.base.BaseFragment
import com.yallam.marvelapp.data.model.CharacterModel
import com.yallam.marvelapp.presentation.characterdetails.CharacterDetailsFragment
import kotlinx.android.synthetic.main.fragment_characters_list.*
import org.koin.android.viewmodel.ext.android.viewModel
import java.io.IOException

class CharactersListFragment : BaseFragment() {

    private val viewModel: CharactersListViewModel by viewModel()
    private val characterListAdapter: CharacterListAdapter by lazy {
        CharacterListAdapter(mutableListOf(), ::charactersListItemClickCallback)
    }
    private var shouldLoadMore = true

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_characters_list, container, false)

        observeViewModelInitialState()
        observeViewModelLoadMoreState()
        viewModel.getCharacters()

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initCharacterListRecyclerView()
        charactersListRecyclerViewPagination()
    }

    private fun initCharacterListRecyclerView() {
        rvCharacters.apply {
            layoutManager = LinearLayoutManager(activity)
            setHasFixedSize(true)
            adapter = characterListAdapter
        }
    }

    private fun charactersListRecyclerViewPagination() {
        rvCharacters.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (shouldLoadMore && !recyclerView.canScrollVertically(1)) {
                    viewModel.getMoreCharacters()
                }
            }
        })
    }

    private fun observeViewModelInitialState() {
        viewModel.observeInitialState(this, Observer { state ->
            when (state) {
                is CharactersListState.Loading -> showLoading()
                is CharactersListState.Ready -> showCharacters(state.characters)
                is CharactersListState.Error -> showError(state.throwable)
            }
        })
    }

    private fun observeViewModelLoadMoreState() {
        viewModel.observeLoadMoreState(this, Observer { state ->
            when (state) {
                is CharactersListState.Loading -> showLoadMoreLoading()
                is CharactersListState.Ready -> showMoreCharacters(state.characters)
            }
        })
    }

    private fun showLoading() {
        tvError.visibility = GONE
        rvCharacters.visibility = GONE
        progressBarLoading.visibility = VISIBLE
    }

    private fun showCharacters(characters: List<CharacterModel>) {
        characterListAdapter.characters = characters.toMutableList()
        characterListAdapter.notifyDataSetChanged()

        progressBarLoading.visibility = GONE
        tvError.visibility = GONE
        rvCharacters.visibility = VISIBLE
    }

    private fun showError(throwable: Throwable) {
        tvError.text = if (throwable is IOException) {
            getString(R.string.internet_connection_error)
        } else {
            getString(R.string.something_wrong_error)
        }

        progressBarLoading.visibility = GONE
        rvCharacters.visibility = GONE
        tvError.visibility = VISIBLE
    }

    private fun charactersListItemClickCallback(character: CharacterModel) {
        fragmentNavigator.navigateToFragmentSaveState(this, CharacterDetailsFragment.newInstance(character))
    }

    private fun showLoadMoreLoading() {

    }

    private fun showMoreCharacters(characters: List<CharacterModel>) {
        characterListAdapter.characters.addAll(characters)
        characterListAdapter.notifyDataSetChanged()
        shouldLoadMore = false
    }

}
