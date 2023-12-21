package com.mldz.photo_impl.domain

import com.mldz.photo_api.domain.BookmarkPhotoUseCase
import org.koin.core.annotation.Factory


@Factory
class BookmarkPhotoUseCaseImpl(
    private val repository: PhotoRepository
) : BookmarkPhotoUseCase {

    override suspend fun invoke(id: String, isBookmark: Boolean): Boolean {
        return repository.bookmarkPhoto(id, isBookmark)
    }
}