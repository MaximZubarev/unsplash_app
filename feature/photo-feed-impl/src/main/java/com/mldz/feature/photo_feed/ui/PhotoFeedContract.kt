package com.mldz.feature.photo_feed.ui

import androidx.paging.PagingData
import com.mldz.core.common.base.UiEffect
import com.mldz.core.common.base.UiEvent
import com.mldz.core.common.base.UiState
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow


interface PhotoFeedContract {

    sealed class Event: UiEvent {
        object OnRepeatLoad: Event()
    }

    data class State(
        val state: PhotoFeedUiState
    ): UiState

    sealed class PhotoFeedUiState {
        data object Loading: PhotoFeedUiState()
        data class Error(val message: String? = ""): PhotoFeedUiState()
        data class Success(val list: Flow<PagingData<Photo>>): PhotoFeedUiState()
    }

    sealed class Effect: UiEffect {

    }
}