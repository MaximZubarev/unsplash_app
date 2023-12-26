package com.mldz.photo_impl.usecase

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.repository.PhotoRepository
import com.mldz.photo_api.usecase.GetBookmarksUseCase
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Factory


@Factory
class GetBookmarksUseCaseImpl(
    private val repository: PhotoRepository
) : GetBookmarksUseCase {

    override fun invoke(): Flow<PagingData<Photo>> {
        return repository.getBookmarks()
    }
}