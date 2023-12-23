package com.mldz.photo_impl.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.map
import com.mldz.core.common.extension.formatFromServerToHuman
import com.mldz.core.db_api.DatabaseSource
import com.mldz.network_api.NetworkApi
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.models.PhotoDetail
import com.mldz.photo_api.models.toPhotoDetail
import com.mldz.photo_impl.domain.PhotoRepository
import com.mldz.photo_impl.paging.SearchPaging
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import org.koin.core.annotation.Named
import org.koin.core.annotation.Single


@Single
internal class PhotoRepositoryImpl(
    private val remoteDataSource: NetworkApi,
    private val localDataSource: DatabaseSource,
    private val pagerFeed: Pager<Int, Photo>,
    @Named("Search") private val pagerSearch: Pager<Int, Photo>
): PhotoRepository {
    override fun getPhotoFeed(): Flow<PagingData<Photo>> {
        return pagerFeed.flow
    }

    override fun getSearchedPhoto(query: String): Flow<PagingData<Photo>> {
        return Pager(
            config = PagingConfig(pageSize = 10, prefetchDistance = 1),
            pagingSourceFactory = {
                SearchPaging(networkApi = remoteDataSource, query = query)
            }
        ).flow
    }

    override fun getBookmarks(page: Int): Flow<PagingData<Photo>> {
        return localDataSource.getBookmarkedPhotos().map {
            it.map { entity -> Photo(entity.id, entity.url) }
        }
    }

    override fun getPhoto(id: String): Flow<PhotoDetail> {
        return flow {
            val photoFromServer = remoteDataSource.getPhotoById(photoId = id)
            val isBookmark = localDataSource.getPhotoById(id) != null
            val photo = toPhotoDetail(photoFromServer, isBookmark) { it?.formatFromServerToHuman() }
            emit(photo)
        }
    }

    override suspend fun likePhoto(id: String, isLike: Boolean): Boolean {
        return if (isLike) {
            remoteDataSource.likePhoto(photoId = id)
        } else {
            remoteDataSource.unlikePhoto(photoId = id)
        }
    }

    override suspend fun bookmarkPhoto(id: String, url: String, isBookmark: Boolean): Boolean {
        return if (isBookmark) {
            localDataSource.bookmarkPhoto(id = id, url = url) > 0
        } else {
            localDataSource.unBookmarkPhoto(id = id) > 0
        }
    }
}