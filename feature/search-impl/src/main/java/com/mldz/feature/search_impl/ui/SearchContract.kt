package com.mldz.feature.search_impl.ui

import androidx.paging.PagingData
import com.mldz.core.common.base.UiEffect
import com.mldz.core.common.base.UiEvent
import com.mldz.core.common.base.UiState
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


interface SearchContract {

    sealed class Event: UiEvent {
        data class OnSearch(val query: String): Event()
        object OnRepeatLoad: Event()
    }

    data class State(
        val idle: Boolean = false,
        val isLoading: Boolean = false,
        val isEmpty: Boolean = false,
        val error: String? = null,
        val searchResult: Flow<PagingData<Photo>> = flowOf(),
        val searchQuery: String = ""
    ): UiState

    sealed class Effect: UiEffect {

    }
}