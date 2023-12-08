package com.mldz.data.repository

import com.mldz.photo_api.models.Photo
import com.mldz.core.network.RemoteSource


class RemotePhotoRepository(
    private val remoteSource: RemoteSource
): PhotoRepository {

    override fun getPhotoFeed(page: Int): List<com.mldz.photo_api.models.Photo> {
        return remoteSource.getPhotoFeed(page = page)
    }
}