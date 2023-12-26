package com.mldz.photo_impl.usecase

import com.mldz.photo_api.repository.PhotoRepository
import com.mldz.photo_api.usecase.LikePhotoUseCase
import org.koin.core.annotation.Factory


@Factory
class LikePhotoUseCaseImpl(
    private val repository: PhotoRepository
): LikePhotoUseCase {

    override suspend fun invoke(photoId: String, isLike: Boolean): Boolean {
        return repository.likePhoto(id = photoId, isLike = isLike)
    }
}