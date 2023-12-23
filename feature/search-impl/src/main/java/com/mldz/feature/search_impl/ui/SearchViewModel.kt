package com.mldz.feature.search_impl.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.mldz.core.common.base.BaseViewModel
import com.mldz.photo_api.domain.SearchPhotoUseCase
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.mapLatest
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
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

    private fun onSearch() {
        viewModelScope.launch {
            searchQuery.asSharedFlow()
                .debounce(500)
                .filter { it.length > 2 }
                .flatMapLatest {
                    setState {
                        currentState.copy(isLoading = true)
                    }
                    flowOf(searchPhotoUseCase.invoke(it))
                }
                .collect { data ->
                    setState {
                        currentState.copy(
                            idle = false,
                            isLoading = false,
                            searchResult = data
                        )
                    }
                }
        }
    }
}