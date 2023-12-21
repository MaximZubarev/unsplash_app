package com.mldz.network_impl.retrofit

import com.mldz.network_api.NetworkApi
import com.mldz.network_api.models.PhotoDetailNetworkModel
import com.mldz.network_api.models.PhotoNetworkModel
import org.koin.core.annotation.Single


@Single
internal class NetworkImpl(
    private val service: RetrofitService
): NetworkApi {

    override suspend fun getPhotos(page: Int): List<PhotoNetworkModel> {
        return service.getPhotos(page = page)
    }

    override suspend fun getPhotoById(photoId: String): PhotoDetailNetworkModel {
        return service.getPhotoById(photoId = photoId)
    }

    override suspend fun likePhoto(photoId: String): Boolean {
        return service.likePhoto(photoId).isSuccessful
    }

    override suspend fun unlikePhoto(photoId: String): Boolean {
        return service.unlikePhoto(photoId).isSuccessful
    }
}