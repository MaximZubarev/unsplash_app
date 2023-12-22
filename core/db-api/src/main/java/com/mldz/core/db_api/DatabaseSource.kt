package com.mldz.core.db_api

import androidx.paging.PagingData
import androidx.paging.PagingSource
import com.mldz.core.db_api.entity.PhotoBookmarkEntity
import kotlinx.coroutines.flow.Flow


interface DatabaseSource {

    suspend fun bookmarkPhoto(id: String, url: String): Long

    suspend fun unBookmarkPhoto(id: String): Int

    suspend fun getPhotoById(id: String): PhotoBookmarkEntity?

    fun getBookmarkedPhotos(): Flow<PagingData<PhotoBookmarkEntity>>
}