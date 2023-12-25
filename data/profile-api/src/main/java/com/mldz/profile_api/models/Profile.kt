package com.mldz.profile_api.models

import androidx.paging.PagingData
import com.mldz.photo_api.models.Photo
import kotlinx.coroutines.flow.Flow


data class Profile(
    val id: String,
    val username: String,
    val name: String,
    val bio: String?,
    val location: String?,
    val likes: Int,
    val totalPhotos: Int,
    val followers: Int,
    val imageUrl: String?,
    val photosFlow: Flow<PagingData<Photo>>?
)