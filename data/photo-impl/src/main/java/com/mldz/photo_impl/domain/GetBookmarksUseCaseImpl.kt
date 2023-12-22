package com.mldz.photo_impl.domain

import androidx.paging.PagingData
import com.mldz.photo_api.domain.GetBookmarksUseCase
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class GetBookmarksUseCaseImpl(
    private val repository: PhotoRepository
) : GetBookmarksUseCase {

    override fun invoke(): Flow<PagingData<Photo>> {
        return repository.getBookmarks(0)
    }
}