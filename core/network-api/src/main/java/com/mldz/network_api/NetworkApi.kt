package com.mldz.network_api

import com.mldz.network_api.models.PhotoDetailNetworkModel
import com.mldz.network_api.models.PhotoNetworkModel


interface NetworkApi {

    suspend fun getPhotos(page: Int): List<PhotoNetworkModel>

    suspend fun getPhotoById(photoId: String): PhotoDetailNetworkModel

    suspend fun likePhoto(photoId: String): Boolean

    suspend fun unlikePhoto(photoId: String): Boolean
}