package com.mldz.favorites_impl.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mldz.core.common.base.BaseViewModel
import com.mldz.photo_api.usecase.GetBookmarksUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
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
        getBookmarksUseCase()
            .cachedIn(viewModelScope)
            .onEach {
                setState {
                    FavoritesContract.State(
                        isLoading = false,
                        list = flowOf(it)
                    )
                }
            }
            .catch {
                setState { FavoritesContract.State(isLoading = false, error = it.message) }
            }
            .launchIn(viewModelScope)
    }
}