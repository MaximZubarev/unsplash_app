package com.mldz.photo_impl.usecase

import com.mldz.photo_api.models.PhotoDetail
import com.mldz.photo_api.repository.PhotoRepository
import com.mldz.photo_api.usecase.GetPhotoByIdUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class GetPhotoByIdUseCaseImpl(
    private val photoRepository: PhotoRepository
) : GetPhotoByIdUseCase {

    override fun invoke(photoId: String): Flow<PhotoDetail> {
        return photoRepository.getPhoto(id = photoId)
    }
}