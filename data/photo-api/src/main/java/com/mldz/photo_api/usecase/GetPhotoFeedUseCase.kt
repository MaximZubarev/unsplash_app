package com.mldz.photo_api.usecase

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow


interface GetPhotoFeedUseCase {

    operator fun invoke(): Flow<PagingData<Photo>>
}