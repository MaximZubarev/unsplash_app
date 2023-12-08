package com.mldz.photo_impl.domain

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow


interface PhotoRepository {

    fun getPhotoFeed(page: Int): Flow<PagingData<Photo>>

    fun getSearchedPhoto(page: Int): Flow<List<Photo>>

    fun getFavoritesPhoto(page: Int): Flow<List<Photo>>

    fun getPhoto(id: String): Flow<Photo>

    suspend fun setLike(id: String): Boolean

    suspend fun addToFavorite(id: String): Boolean
}