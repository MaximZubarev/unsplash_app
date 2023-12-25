package com.mldz.photo_impl.paging

import com.mldz.network_api.NetworkApi
import com.mldz.network_api.models.PhotoNetworkModel
import org.koin.core.annotation.Factory


@Factory
class UserPhotosPaging(
    private val networkApi: NetworkApi,
    private val username: String
) : PhotoPaging<PhotoNetworkModel>() {

    override suspend fun getData(page: Int): List<PhotoNetworkModel> {
        return networkApi.getUserPhotos(username, page)
    }
}