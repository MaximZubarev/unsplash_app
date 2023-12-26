package com.mldz.photo_api.repository

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.models.PhotoDetail
import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    fun getPhotoFeed(): Flow<PagingData<Photo>>

    fun getSearchedPhoto(query: String): Flow<PagingData<Photo>>

    fun getBookmarks(): Flow<PagingData<Photo>>

    fun getPhoto(id: String): Flow<PhotoDetail>

    suspend fun likePhoto(id: String, isLike: Boolean): Boolean

    suspend fun bookmarkPhoto(id: String, url: String, isBookmark: Boolean): Boolean

    fun getUserPhotos(username: String): Flow<PagingData<Photo>>
}