package com.mldz.network_impl.retrofit

import com.mldz.network_api.models.PhotoNetworkModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface RetrofitService {

    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int
    ): List<PhotoNetworkModel>
}