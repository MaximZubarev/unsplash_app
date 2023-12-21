package com.mldz.photo_impl.domain

import com.mldz.photo_api.domain.LikePhotoUseCase
import org.koin.core.annotation.Factory


@Factory
class LikePhotoUseCaseImpl(
    private val repository: PhotoRepository
): LikePhotoUseCase {

    override suspend fun invoke(photoId: String, isLike: Boolean): Boolean {
        return repository.likePhoto(id = photoId, isLike = isLike)
    }
}