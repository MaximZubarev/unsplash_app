package com.mldz.photo_impl.usecase

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.repository.PhotoRepository
import com.mldz.photo_api.usecase.GetPhotoFeedUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class GetPhotoFeedUseCaseImpl(
    private val photoRepository: PhotoRepository
) : GetPhotoFeedUseCase {

    override operator fun invoke(): Flow<PagingData<Photo>> {
        return photoRepository.getPhotoFeed()
    }
}