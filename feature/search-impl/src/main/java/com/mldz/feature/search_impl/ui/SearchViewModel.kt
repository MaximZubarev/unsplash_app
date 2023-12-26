package com.mldz.feature.search_impl.ui

import androidx.lifecycle.viewModelScope
import com.mldz.core.common.base.BaseViewModel
import com.mldz.photo_api.usecase.SearchPhotoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class SearchViewModel(
    private val searchPhotoUseCase: SearchPhotoUseCase
) : BaseViewModel<SearchContract.Event, SearchContract.State, SearchContract.Effect>() {

    private val searchQuery = MutableSharedFlow<String>(1)

    init {
        onSearch()
    }

    override fun createInitialState(): SearchContract.State {
        return SearchContract.State(idle = true)
    }

    override fun handleEvent(event: SearchContract.Event) {
        when (event) {
            is SearchContract.Event.OnSearch -> {
                searchQuery.tryEmit(event.query)
                setState {
                    currentState.copy(idle = false, searchQuery = event.query)
                }
            }

            is SearchContract.Event.OnRepeatLoad -> {
                searchQuery.tryEmit(currentState.searchQuery)
            }

            else -> {}
        }
    }

    @OptIn(FlowPreview::class, ExperimentalCoroutinesApi::class)
    private fun onSearch() {
        searchQuery
            .debounce(500)
            .filter { it.length > 2 }
            .flatMapLatest {
                setState {
                    currentState.copy(isLoading = true)
                }
                flowOf(searchPhotoUseCase(it))
            }
            .onEach {
                setState {
                    currentState.copy(
                        idle = false,
                        isLoading = false,
                        searchResult = it
                    )
                }
            }
            .launchIn(viewModelScope)
    }
}