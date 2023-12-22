package com.mldz.photo_api.models

import com.mldz.network_api.models.PhotoNetworkModel

data class Photo(
    val id: String,
    val url: String
)

fun toPhoto(photoApi: PhotoNetworkModel) = Photo(
    id = photoApi.id,
    url = photoApi.urls.regular
)