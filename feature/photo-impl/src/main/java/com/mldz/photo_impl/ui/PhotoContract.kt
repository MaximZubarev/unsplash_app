package com.mldz.photo_impl.ui

import com.mldz.core.common.base.UiEffect
import com.mldz.core.common.base.UiEvent
import com.mldz.core.common.base.UiState
import com.mldz.photo_api.models.PhotoDetail


interface PhotoContract {
    sealed class Event: UiEvent {
        object OnRepeatLoad: Event()
        object OnShowDetails: Event()
        object OnLike: Event()
        object OnBookmark: Event()

        data object OnShareClicked: Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val error: String? = null,
        val photo: PhotoDetail? = null,
        val showDetails: Boolean = false
    ): UiState

    sealed class Effect: UiEffect {
        object ShowLiked: Effect()
        object ShowBookmarked: Effect()

        data class NavigateToShare(val title: String?, val link: String?) : Effect()
    }
}