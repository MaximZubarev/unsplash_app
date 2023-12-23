package com.mldz.network_impl.retrofit

import com.mldz.network_api.models.PhotoDetailNetworkModel
import com.mldz.network_api.models.PhotoNetworkModel
import com.mldz.network_api.models.SearchResultNetworkModel
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query


interface RetrofitService {

    @GET("/photos")
    suspend fun getPhotos(
        @Query("page") page: Int
    ): List<PhotoNetworkModel>

    @GET("/photos/{id}")
    suspend fun getPhotoById(
        @Path("id") photoId: String
    ): PhotoDetailNetworkModel

    @POST("/photos/{id}/like")
    suspend fun likePhoto(
        @Path("id") photoId: String
    ): Response<Unit>

    @DELETE("/photos/{id}/like")
    suspend fun unlikePhoto(
        @Path("id") photoId: String
    ): Response<Unit>

    @GET("/search/photos")
    suspend fun searchPhoto(
        @Query("query") query: String,
        @Query("page") page: Int
    ): SearchResultNetworkModel
}