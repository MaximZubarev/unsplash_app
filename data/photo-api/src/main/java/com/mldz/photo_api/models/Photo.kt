package com.mldz.photo_api.models

import com.mldz.network_api.models.PhotoNetworkModel

data class Photo(
    val id: String,
    val urls: Paths
)

fun toPhoto(photoApi: PhotoNetworkModel) = Photo(
    id = photoApi.id,
    urls = Paths(
        raw = photoApi.urls.raw,
        regular = photoApi.urls.regular,
        full = photoApi.urls.full
    )
)