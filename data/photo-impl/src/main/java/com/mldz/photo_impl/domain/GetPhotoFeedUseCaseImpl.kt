package com.mldz.photo_impl.domain

import androidx.paging.PagingData
import com.mldz.photo_api.domain.GetPhotoFeedUseCase
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOn
import org.koin.core.annotation.Factory


@Factory
class GetPhotoFeedUseCaseImpl(
    private val photoRepository: PhotoRepository,
    private val ioDispatcher: CoroutineDispatcher
): GetPhotoFeedUseCase {

    override operator fun invoke(): Flow<PagingData<Photo>> {
        return photoRepository.getPhotoFeed().flowOn(ioDispatcher)
    }
}