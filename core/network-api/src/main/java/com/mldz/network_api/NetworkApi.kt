package com.mldz.network_api

import com.mldz.network_api.models.PhotoDetailNetworkModel
import com.mldz.network_api.models.PhotoNetworkModel
import com.mldz.network_api.models.SearchResultNetworkModel
import com.mldz.network_api.models.UserProfileNetworkModel


interface NetworkApi {

    suspend fun getPhotos(page: Int): List<PhotoNetworkModel>

    suspend fun getPhotoById(photoId: String): PhotoDetailNetworkModel

    suspend fun likePhoto(photoId: String): Boolean

    suspend fun unlikePhoto(photoId: String): Boolean

    suspend fun search(query: String, page: Int): SearchResultNetworkModel

    suspend fun getUserProfile(username: String): UserProfileNetworkModel

    suspend fun getUserPhotos(username: String, page: Int): List<PhotoNetworkModel>
}