package com.mldz.feature.photo_feed.ui

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.mldz.core.common.base.BaseViewModel
import com.mldz.photo_api.usecase.GetPhotoFeedUseCase
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
internal class PhotoFeedViewModel(
    private val getPhotoFeedUseCase: GetPhotoFeedUseCase,
) : BaseViewModel<PhotoFeedContract.Event, PhotoFeedContract.State, PhotoFeedContract.Effect>() {

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        getPhotoFeedUseCase()
            .cachedIn(viewModelScope)
            .onEach {
                val state = PhotoFeedContract.PhotoFeedUiState.Success(flowOf(it))
                setState {
                    copy(state = state)
                }
            }
            .launchIn(viewModelScope)
    }

    override fun createInitialState(): PhotoFeedContract.State {
        return PhotoFeedContract.State(PhotoFeedContract.PhotoFeedUiState.Loading)
    }

    override fun handleEvent(event: PhotoFeedContract.Event) {
        when (event) {
            is PhotoFeedContract.Event.OnRepeatLoad -> {
                loadPhotos()
            }
        }
    }
}
