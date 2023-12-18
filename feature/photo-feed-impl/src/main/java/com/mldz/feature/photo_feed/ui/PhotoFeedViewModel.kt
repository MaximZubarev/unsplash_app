package com.mldz.feature.photo_feed.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.mldz.photo_api.domain.GetPhotoFeedUseCase
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
internal class PhotoFeedViewModel(
    private val getPhotoFeedUseCase: GetPhotoFeedUseCase,
): ViewModel() {

    private val _uiState = MutableStateFlow<PhotoFeedUiState>(PhotoFeedUiState.Loading)
    val uiState = _uiState.asStateFlow()

    init {
        loadPhotos(page = 1)
    }

    fun loadPhotos(page: Int) {
        viewModelScope.launch {
            getPhotoFeedUseCase(page = page)
                .cachedIn(viewModelScope)
                .collect {
//                    val state = when (it) {
//                        is PagingData. -> PhotoFeedUiState.Loading
//                        is Result.Success -> PhotoFeedUiState.Success(it)
//                        is Result.Error -> PhotoFeedUiState.Error
//                    }
//                    _uiState.update { state }
                    val state = PhotoFeedUiState.Success(flowOf(it))
                    _uiState.update { state }
                }
        }
    }
}

internal sealed interface PhotoFeedUiState {
    data object Loading: PhotoFeedUiState
    data object Error: PhotoFeedUiState
    data class Success(val list: Flow<PagingData<Photo>>): PhotoFeedUiState
}