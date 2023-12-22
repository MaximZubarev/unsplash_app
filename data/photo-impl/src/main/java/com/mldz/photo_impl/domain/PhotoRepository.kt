package com.mldz.photo_impl.domain

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.models.PhotoDetail
import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    fun getPhotoFeed(): Flow<PagingData<Photo>>

    fun getSearchedPhoto(page: Int): Flow<List<Photo>>

    fun getBookmarks(page: Int): Flow<PagingData<Photo>>

    fun getPhoto(id: String): Flow<PhotoDetail>

    suspend fun likePhoto(id: String, isLike: Boolean): Boolean

    suspend fun bookmarkPhoto(id: String, url: String, isBookmark: Boolean): Boolean
}