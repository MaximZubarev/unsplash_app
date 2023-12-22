package com.mldz.favorites_impl.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mldz.core.common.base.BaseViewModel
import com.mldz.photo_api.domain.GetBookmarksUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class FavoritesViewModel(
    private val getBookmarksUseCase: GetBookmarksUseCase
) : BaseViewModel<FavoritesContract.Event, FavoritesContract.State, FavoritesContract.Effect>() {

    init {
        loadPhotos()
    }

    override fun createInitialState(): FavoritesContract.State {
        return FavoritesContract.State(isLoading = true)
    }

    override fun handleEvent(event: FavoritesContract.Event) {
        TODO("Not yet implemented")
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            getBookmarksUseCase.invoke()
                .cachedIn(viewModelScope)
                .catch {
                    setState { FavoritesContract.State(isLoading = false, error = it.message) }
                }
                .collect {
                    setState {
                        FavoritesContract.State(
                            isLoading = false,
                            list = flowOf(it)
                        )
                    }
                }
        }
    }
}