package com.mldz.network_api

import com.mldz.network_api.models.PhotoNetworkModel


interface NetworkApi {

    suspend fun getPhotos(page: Int): List<PhotoNetworkModel>
}