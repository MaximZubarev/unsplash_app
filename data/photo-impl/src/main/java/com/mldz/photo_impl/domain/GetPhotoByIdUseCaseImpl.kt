package com.mldz.photo_impl.domain

import com.mldz.photo_api.domain.GetPhotoByIdUseCase
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.models.PhotoDetail
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory


@Factory
class GetPhotoByIdUseCaseImpl(
    private val photoRepository: PhotoRepository,
    private val ioDispatcher: CoroutineDispatcher
): GetPhotoByIdUseCase {
    override fun invoke(photoId: String): Flow<PhotoDetail> {
        return photoRepository.getPhoto(id = photoId).flowOn(ioDispatcher)
    }
}