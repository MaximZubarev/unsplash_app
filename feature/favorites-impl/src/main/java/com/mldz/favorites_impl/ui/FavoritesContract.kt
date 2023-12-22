package com.mldz.favorites_impl.ui

import androidx.paging.PagingData
import com.mldz.core.common.base.UiEffect
import com.mldz.core.common.base.UiEvent
import com.mldz.core.common.base.UiState
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.models.PhotoDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf


interface FavoritesContract {

    sealed class Event: UiEvent {
        object OnRepeatLoad: Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val list: Flow<PagingData<Photo>> = flowOf()
    ): UiState

    sealed class Effect: UiEffect {

    }
}