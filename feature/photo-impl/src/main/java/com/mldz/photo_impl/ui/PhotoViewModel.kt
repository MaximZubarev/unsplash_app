package com.mldz.photo_impl.ui

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.mldz.core.common.base.BaseViewModel
import com.mldz.photo_api.usecase.BookmarkPhotoUseCase
import com.mldz.photo_api.usecase.GetPhotoByIdUseCase
import com.mldz.photo_api.usecase.LikePhotoUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel


@KoinViewModel
class PhotoViewModel(
    savedStateHandle: SavedStateHandle,
    private val getPhotoUseCase: GetPhotoByIdUseCase,
    private val likePhotoUseCase: LikePhotoUseCase,
    private val bookmarkPhotoUseCase: BookmarkPhotoUseCase
) : BaseViewModel<PhotoContract.Event, PhotoContract.State, PhotoContract.Effect>() {

    private val id: String = checkNotNull(savedStateHandle["photoIdArg"])

    private val idState = MutableStateFlow(id)

    init {
        loadPhoto()
    }

    override fun createInitialState(): PhotoContract.State {
        return PhotoContract.State(isLoading = true)
    }

    override fun handleEvent(event: PhotoContract.Event) {
        when (event) {
            is PhotoContract.Event.OnRepeatLoad -> loadPhoto()
            is PhotoContract.Event.OnLike -> likePhoto()
            is PhotoContract.Event.OnBookmark -> bookmarkPhoto()
            is PhotoContract.Event.OnShowDetails -> {
                val show = !currentState.showDetails
                setState { currentState.copy(showDetails = show) }
            }
            is PhotoContract.Event.OnShareClicked -> setEffect {
                PhotoContract.Effect.NavigateToShare(
                    currentState.photo?.title,
                    currentState.photo?.link
                )
            }
        }
    }

    @OptIn(ExperimentalCoroutinesApi::class)
    private fun loadPhoto() {
        idState
            .flatMapLatest {
                getPhotoUseCase(photoId = it)
            }
            .onEach {
                setState { currentState.copy(isLoading = false, error = null, photo = it, showDetails = false) }
            }
            .catch {
                setState { currentState.copy(isLoading = false, error = it.message) }
            }
            .launchIn(viewModelScope)
    }

    private fun likePhoto() {
        viewModelScope.launch {
            currentState.photo?.let {
                val res = likePhotoUseCase(it.id, !it.likedByUser)
                if (res) {
                    setState { currentState.copy(photo = photo?.copy(likedByUser = !it.likedByUser)) }
                    setEffect { PhotoContract.Effect.ShowLiked }
                }
            }
        }
    }

    private fun bookmarkPhoto() {
        viewModelScope.launch {
            currentState.photo?.let {
                val res = bookmarkPhotoUseCase.invoke(it.id, it.urls.regular, !it.isBookmark)
                if (res) {
                    if (!it.isBookmark) {
                        setEffect { PhotoContract.Effect.ShowBookmarked }
                    }
                    setState { currentState.copy(photo = photo?.copy(isBookmark = !it.isBookmark)) }
                }
            }
        }
    }
}