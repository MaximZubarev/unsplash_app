package com.mldz.feature.photo_feed.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import androidx.paging.map
import com.mldz.core.common.base.BaseViewModel
import com.mldz.photo_api.domain.GetPhotoFeedUseCase
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
internal class PhotoFeedViewModel(
    private val getPhotoFeedUseCase: GetPhotoFeedUseCase,
): BaseViewModel<PhotoFeedContract.Event, PhotoFeedContract.State, PhotoFeedContract.Effect>() {

    init {
        loadPhotos()
    }

    private fun loadPhotos() {
        viewModelScope.launch {
            getPhotoFeedUseCase()
                .cachedIn(viewModelScope)
                .collect {
                    val state = PhotoFeedContract.PhotoFeedUiState.Success(flowOf(it))
                    setState {
                       copy(state = state)
                    }
                }
        }
    }

    override fun createInitialState(): PhotoFeedContract.State {
        return PhotoFeedContract.State(PhotoFeedContract.PhotoFeedUiState.Loading)
    }

    override fun handleEvent(event: PhotoFeedContract.Event) {
        when (event) {
//            is PhotoFeedContract.Event.OnOpenPhoto -> {  }
            is PhotoFeedContract.Event.OnRepeatLoad -> { loadPhotos() }
            is PhotoFeedContract.Event.OnSearch -> {  }
        }
    }
}
