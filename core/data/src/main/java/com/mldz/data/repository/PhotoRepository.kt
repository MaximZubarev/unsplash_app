package com.mldz.data.repository

import com.mldz.photo_api.models.Photo


interface PhotoRepository {

    fun getPhotoFeed(page: Int): List<com.mldz.photo_api.models.Photo>
}