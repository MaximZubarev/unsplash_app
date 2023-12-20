package com.mldz.photo_impl.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.mldz.network_api.NetworkApi
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.models.toPhoto
import org.koin.core.annotation.Factory


@Factory
class PhotoFeedPaging(
    private val remoteDataSource: NetworkApi
) : PagingSource<Int, Photo>() {

    override fun getRefreshKey(state: PagingState<Int, Photo>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Photo> {
        return try {
            val currentPage = params.key ?: 1
            val photos = remoteDataSource.getPhotos(page = currentPage)
            LoadResult.Page(
                data = photos.map { toPhoto(it) },
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (photos.isEmpty()) null else currentPage.plus(1)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}