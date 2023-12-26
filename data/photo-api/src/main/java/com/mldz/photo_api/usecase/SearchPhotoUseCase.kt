package com.mldz.photo_api.usecase

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow


interface SearchPhotoUseCase {

    suspend operator fun invoke(query: String): Flow<PagingData<Photo>>
}