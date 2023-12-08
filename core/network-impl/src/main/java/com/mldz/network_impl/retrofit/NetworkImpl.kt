package com.mldz.network_impl.retrofit

import com.mldz.network_api.NetworkApi
import com.mldz.network_api.models.PhotoNetworkModel
import org.koin.core.annotation.Single


@Single
internal class NetworkImpl(
    private val service: RetrofitService
): NetworkApi {

    override suspend fun getPhotos(page: Int): List<PhotoNetworkModel> {
        return service.getPhotos(page = page)
    }
}