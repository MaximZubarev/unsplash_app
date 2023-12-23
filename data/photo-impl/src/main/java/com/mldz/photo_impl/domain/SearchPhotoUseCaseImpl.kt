package com.mldz.photo_impl.domain

import androidx.paging.PagingData
import com.mldz.photo_api.domain.SearchPhotoUseCase
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class SearchPhotoUseCaseImpl(
    private val repository: PhotoRepository
) : SearchPhotoUseCase {

    override suspend fun invoke(query: String): Flow<PagingData<Photo>> {
        return repository.getSearchedPhoto(query)
    }
}