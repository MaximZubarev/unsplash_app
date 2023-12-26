package com.mldz.photo_impl.usecase

import com.mldz.photo_api.repository.PhotoRepository
import com.mldz.photo_api.usecase.BookmarkPhotoUseCase
import org.koin.core.annotation.Factory


@Factory
class BookmarkPhotoUseCaseImpl(
    private val repository: PhotoRepository
) : BookmarkPhotoUseCase {

    override suspend fun invoke(id: String, url: String, isBookmark: Boolean): Boolean {
        return repository.bookmarkPhoto(id, url, isBookmark)
    }
}