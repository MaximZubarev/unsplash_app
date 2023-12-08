package com.mldz.photo_impl.data

import androidx.paging.Pager
import androidx.paging.PagingData
import com.mldz.network_api.NetworkApi
import com.mldz.photo_api.models.Photo
import com.mldz.photo_impl.domain.PhotoRepository
import kotlinx.coroutines.flow.Flow
import org.koin.core.annotation.Single


@Single
internal class PhotoRepositoryImpl(
    private val remoteDataSource: NetworkApi,
    private val pager: Pager<Int, Photo>
): PhotoRepository {
    override fun getPhotoFeed(page: Int): Flow<PagingData<Photo>> {
        return pager.flow
    }

    override fun getSearchedPhoto(page: Int): Flow<List<Photo>> {
        TODO("Not yet implemented")
    }

    override fun getFavoritesPhoto(page: Int): Flow<List<Photo>> {
        TODO("Not yet implemented")
    }

    override fun getPhoto(id: String): Flow<Photo> {
        TODO("Not yet implemented")
    }

    override suspend fun setLike(id: String): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun addToFavorite(id: String): Boolean {
        TODO("Not yet implemented")
    }

}