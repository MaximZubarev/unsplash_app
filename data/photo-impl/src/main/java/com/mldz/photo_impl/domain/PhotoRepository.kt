package com.mldz.photo_impl.domain

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import com.mldz.photo_api.models.PhotoDetail
import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    fun getPhotoFeed(): Flow<PagingData<Photo>>

    fun getSearchedPhoto(page: Int): Flow<List<Photo>>

    fun getFavoritesPhoto(page: Int): Flow<List<Photo>>

    fun getPhoto(id: String): Flow<PhotoDetail>

    suspend fun likePhoto(id: String, isLike: Boolean): Boolean

    suspend fun bookmarkPhoto(id: String, isBookmark: Boolean): Boolean
}