package com.mldz.photo_impl.paging

import androidx.paging.PagingSource
import androidx.paging.PagingState


abstract class PhotoPaging<K: Any> : PagingSource<Int, K>() {

    override fun getRefreshKey(state: PagingState<Int, K>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, K> {
        return try {
            val currentPage = params.key ?: 1
            val photos = getData(page = currentPage)
            LoadResult.Page(
                data = photos,
                prevKey = if (currentPage == 1) null else currentPage - 1,
                nextKey = if (photos.isEmpty()) null else currentPage.plus(1)
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }

    abstract suspend fun getData(page: Int): List<K>
}